package top.xianyume.iwe.backend.controller;

import cn.dev33.satoken.annotation.SaCheckOr;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.xianyume.iwe.backend.model.dto.UserLoginDTO;
import top.xianyume.iwe.backend.model.dto.UserQueryDTO;
import top.xianyume.iwe.backend.model.dto.UserSignupDTO;
import top.xianyume.iwe.backend.model.dto.UserUpdateDTO;
import top.xianyume.iwe.backend.model.vo.UserPublicVO;
import top.xianyume.iwe.backend.service.intf.UserService;


/**
 *
 * @author xianyume
 */
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取目标单个用户信息
     */
    @GetMapping("/{id}")
    public SaResult getUserPublicInfo(@PathVariable Integer id) {
        UserPublicVO user = userService.getUserPublicInfo(id);
        return SaResult.ok("获取单个用户成功")
                .setData(user);
    }

    /**
     * 获取目标多个用户信息
     */
    @GetMapping
    public SaResult getUserPublicInfoList(@ModelAttribute @Valid UserQueryDTO user) {
        IPage<UserPublicVO> userList = userService.getUserPublicInfoList(
                user.getNickname(), user.getPageNum(), user.getPageSize());
        return SaResult.ok("获取多个用户成功")
                .setData(userList);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    @SaIgnore
    public SaResult login(@ModelAttribute @Valid UserLoginDTO user) {
        userService.login(user.getUsername(), user.getPassword(), user.getVerifyCode());
        String token = StpUtil.getTokenValue();
        return SaResult.ok("登录成功")
                .setData(token);
    }
    /**
     * 注册
     */
    @PostMapping
    @SaIgnore
    public SaResult signUp(@ModelAttribute @Valid UserSignupDTO user) {
        userService.signUp(user.getUsername(), user.getPassword(), user.getVerifyCode());
        return SaResult.ok("注册成功，请继续登录！");
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    @SaCheckOr(
            role = @SaCheckRole("user"),
            permission = @SaCheckPermission("user.logout")
    )
    public SaResult logout() {
        userService.logout();
        return SaResult.ok("登出成功");
    }


    /**
     * 修改用户信息
     */
    @PutMapping
    @SaCheckOr(
            role = @SaCheckRole("user"),
            permission = @SaCheckPermission("user.update")
    )
    public SaResult updateUserInfo(@ModelAttribute UserUpdateDTO user) {
        userService.updateUserInfo(user.getNickname(), user.getDescription(), user.getEmail());
        return SaResult.ok("修改用户信息成功");
    }

    @GetMapping("/me")
    @SaIgnore
    public SaResult me() {
        if (StpUtil.isLogin()) {
            UserPublicVO user = userService.getUserPublicInfo(StpUtil.getLoginIdAsInt());
            return SaResult.ok("获取用户成功")
                    .setData(user);
        }
        else {
            return SaResult.error("未登录");
        }
    }
}
