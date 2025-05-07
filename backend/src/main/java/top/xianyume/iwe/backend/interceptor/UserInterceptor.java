package top.xianyume.iwe.backend.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.xianyume.iwe.backend.context.UserContext;
import top.xianyume.iwe.backend.model.entity.User;

/**
 * @author Xianyume
 * @date 2025/05/03 21:45
 **/
@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {

        User user = getUserFromRequest();
        if (user != null) {
            UserContext.setCurrentUser(user);
        }
        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                @NonNull Object handler, Exception ex) {
        // 请求完成后清理ThreadLocal，防止内存泄漏
        UserContext.clear();
    }

    /**
     * 从请求中获取用户信息
     */
    private User getUserFromRequest() {
        try {
            if (StpUtil.isLogin()) {
                User user = new User();
                user.setId(StpUtil.getLoginIdAsInt());
                return user;
            }
        } catch (Exception ignored) {

        }
        return null;
    }

}

