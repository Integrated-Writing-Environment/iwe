package top.xianyume.iwe.backend.service.intf;

import top.xianyume.iwe.backend.model.dto.UserLoginDTO;
import top.xianyume.iwe.backend.model.dto.UserUpdateDTO;
import top.xianyume.iwe.backend.model.vo.UserInfoVO;

import java.util.List;

public interface UserService {
    Boolean checkOldPassword(String oldPassword);
    Boolean login(UserLoginDTO user, String password);
    void logout();
    Integer sign(UserLoginDTO user);
    void update(UserUpdateDTO user);
    void updatePassword(String newPassword);
    void updateAvatar(Integer id, String avatar);
    UserInfoVO infoById(Integer id);
    UserInfoVO infoByUsername(String username);
    UserInfoVO infoByNickname(String nickname);
    List<UserInfoVO> page(UserInfoVO user, int offset, int size);
}
