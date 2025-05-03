package top.xianyume.iwe.backend.context;

import top.xianyume.iwe.backend.model.entity.User;

/**
 * @author Xianyume
 * @date 2025/05/03 21:41
 **/
public class UserContext {

    private static final ThreadLocal<User> CURRENT_USER = new ThreadLocal<>();

    public static void setCurrentUser(User user) {
        CURRENT_USER.set(user);
    }

    public static User getCurrentUser() {
        return CURRENT_USER.get();
    }

    public static void clear() {
        CURRENT_USER.remove();
    }
}

