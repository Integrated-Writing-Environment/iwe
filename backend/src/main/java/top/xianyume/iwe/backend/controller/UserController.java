package top.xianyume.iwe.backend.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.xianyume.iwe.backend.mapper.UserMapper;
import top.xianyume.iwe.backend.model.entity.User;

@RestController
@RequestMapping("/user/")
public class UserController {



    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST
    )
    public SaResult login(@RequestBody User user) {

//        userMapper.selectOne()

        System.out.println(user);
        //StpUtil.login(10001);
        System.out.println("登录成功");
        return SaResult.ok("登录成功");
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}
