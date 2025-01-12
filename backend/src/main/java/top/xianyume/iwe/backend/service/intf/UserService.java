package top.xianyume.iwe.backend.service.intf;

import top.xianyume.iwe.backend.model.dto.UserLoginDTO;
import top.xianyume.iwe.backend.model.dto.UserUpdateDTO;
import top.xianyume.iwe.backend.model.vo.UserInfoVO;

import java.util.List;

public interface UserService {
    Boolean login(UserLoginDTO user, String password);
    void logout();
    Integer sign(UserLoginDTO user);
    void update(UserUpdateDTO user);
    void updatePassword(Integer id, String newPassword);
    void updateAvatar(Integer id, String avatar);
    UserInfoVO infoById(Integer id);
    UserInfoVO infoByUsername(String username);
    List<UserInfoVO> page(UserInfoVO user, int offset, int size);
}
