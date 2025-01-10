package top.xianyume.iwe.backend.service.intf;

import top.xianyume.iwe.backend.model.entity.User;
import top.xianyume.iwe.backend.model.vo.UserInfoVO;

import java.util.List;

public interface UserService {
    Boolean login(User user, String password);
    void logout();
    void signUp(User user);
    void update(User user);
    void updatePassword(User user, String newPassword);
    UserInfoVO info(User user);
    List<UserInfoVO> page(UserInfoVO user, int offset, int size);
}
