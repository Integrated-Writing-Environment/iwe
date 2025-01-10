package top.xianyume.iwe.backend.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.xianyume.iwe.backend.model.dto.UserLoginDTO;
import top.xianyume.iwe.backend.model.entity.User;
import top.xianyume.iwe.backend.model.vo.UserInfoVO;
import top.xianyume.iwe.backend.service.intf.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST
    )
    public SaResult login(@RequestBody UserLoginDTO user) {
        if (user.getUsername().isBlank() || user.getPassword().isBlank()) {
            return SaResult.error("请输入账号和密码");
        }
        UserInfoVO userFromDb = userService.infoByUsername(user.getUsername());
        if (userFromDb == null) {
            return SaResult.error("账号不存在");
        }
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
    public SaResult info(@RequestBody UserLoginDTO user) {
        UserInfoVO userInfo = userService.infoByUsername(user.getUsername());
        System.out.println(userInfo);
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
        userService.logout();
        return SaResult.ok("成功退出登录");
    }
}
