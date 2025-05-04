package top.xianyume.iwe.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SaInterceptor(handle -> SaRouter
//                .match("/**")
//                .notMatch("/user/login")
//                .notMatch("/user/sign")
//                .notMatch("/users/test1")
//                .check(r -> StpUtil.checkLogin())));
    }
}
