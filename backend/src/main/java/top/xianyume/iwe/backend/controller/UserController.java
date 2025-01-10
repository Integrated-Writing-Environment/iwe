package top.xianyume.iwe.backend.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.xianyume.iwe.backend.model.dto.UserLoginDTO;
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
    public SaResult sign(@RequestBody UserLoginDTO user) {
        UserInfoVO userFromDb = userService.infoByUsername(user.getUsername());
        if (userFromDb != null) {
            return SaResult.error("注册失败，账号已经存在");
        }
        userService.sign(user);
        return SaResult.ok("成功注册");
    }

}
