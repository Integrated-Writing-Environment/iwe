package top.xianyume.iwe.backend.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.xianyume.iwe.backend.model.dto.UserLoginDTO;
import top.xianyume.iwe.backend.model.dto.UserUpdateDTO;
import top.xianyume.iwe.backend.model.vo.UserInfoVO;
import top.xianyume.iwe.backend.service.intf.UserService;


@RestController
@RequestMapping("/user/")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST
    )
    public SaResult login(@RequestBody @Valid UserLoginDTO user) {
        UserInfoVO userFromDb = userService.infoByUsername(user.getUsername());
        if (!userService.login(user, user.getPassword())) {
            return SaResult.error("请检查您的账号或密码");
        }
        StpUtil.login(userFromDb.getId());
        return SaResult.ok()
                .setData(StpUtil.getTokenValue())
                .setMsg("登录成功");
    }

    @RequestMapping(
            value = "/info",
            method = RequestMethod.POST
    )
    public SaResult info(@RequestBody JsonNode jsonNode) {
        String username = jsonNode.get("username").asText();
        UserInfoVO userInfo = userService.infoByUsername(username);
        if (userInfo.getId() == null) {
            return SaResult.error("用户不存在");
        }
        return SaResult
                .ok("查询用户成功")
                .setData(userInfo);
    }

    @RequestMapping(
            value = "/logout",
            method = RequestMethod.POST)
    public SaResult logout() {
        StpUtil.logout();
        userService.logout();
        return SaResult.ok("成功退出登录");
    }

    @RequestMapping(
            value = "/sign",
            method = RequestMethod.POST)
    public SaResult sign(@RequestBody @Valid UserLoginDTO user) {
        UserInfoVO userFromDb = userService.infoByUsername(user.getUsername());
        if (userFromDb.getId() != null) {
            return SaResult.error("注册失败，账号已经存在");
        }
        Integer returnId = userService.sign(user);
        StpUtil.login(returnId);
        return SaResult.ok()
                .setData(StpUtil.getTokenValue())
                .setMsg("成功注册并登录");
    }

    @RequestMapping(
            value = "/update-password",
            method = RequestMethod.POST)
    public SaResult updatePassword(@RequestBody JsonNode jsonNode) {
        String oldPassword = jsonNode.get("oldPassword").asText();
        String newPassword = jsonNode.get("newPassword").asText();
        if (oldPassword.isBlank() || newPassword.isBlank()) {
            return SaResult.error("请输入旧密码和新密码");
        }
        if (newPassword.length() < 4 || newPassword.length() > 32) {
            return SaResult.error("密码长度必须在 8 到 32 个字符之间");
        }
        if (newPassword.equals(oldPassword)) {
            return SaResult.error("新密码和旧密码不能相同");
        }
        if (!userService.checkOldPassword(oldPassword)) {
            return SaResult.error("更新密码失败，旧密码错误");
        }
        userService.updatePassword(newPassword);
        return SaResult.ok("更新密码成功");
    }

    @RequestMapping(
            value = "/edit",
            method = RequestMethod.POST)
    public SaResult updateUserInfo(@RequestBody @Valid UserUpdateDTO user) {
        UserInfoVO userFromDb = userService.infoByNickname(user.getNickname());
        if (userFromDb.getId() != null) {
            return SaResult.error("更新用户信息失败，相同的昵称已经被占用");
        }
        userService.update(user);
        return SaResult.ok("用户信息更新成功");
    }
}
