package top.xianyume.iwe.backend.controller;

import cn.dev33.satoken.util.SaResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.xianyume.iwe.backend.context.UserContext;
import top.xianyume.iwe.backend.model.entity.User;
import top.xianyume.iwe.backend.service.intf.UserService;


/**
 *
 * @author xianyume
 */
@RestController
@RequestMapping("/users/")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test1")
    public SaResult test1() {
        User user = new User();
        user.setUsername("aaddcc");
        UserContext.setCurrentUser(user);
        userService.say("a");
        return SaResult.ok("test1");
    }
}
