package top.xianyume.iwe.backend.service.intf;

import top.xianyume.iwe.backend.model.dto.UserLoginDTO;
import top.xianyume.iwe.backend.model.dto.UserUpdateDTO;
import top.xianyume.iwe.backend.model.vo.UserProfileVO;

import java.util.List;

public interface UserService {
    Boolean checkOldPassword(String oldPassword);
    Boolean login(UserLoginDTO user, String password);
    void logout();
    Integer sign(UserLoginDTO user);
    void update(UserUpdateDTO user);
    void updatePassword(String newPassword);
    void updateAvatar(Integer id, String avatar);
    UserProfileVO infoById(Integer id);
    UserProfileVO infoByUsername(String username);
    UserProfileVO infoByNickname(String nickname);
    List<UserProfileVO> page(UserProfileVO user, int offset, int size);
}
