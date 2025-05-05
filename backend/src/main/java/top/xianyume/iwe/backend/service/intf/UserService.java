package top.xianyume.iwe.backend.service.intf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.xianyume.iwe.backend.model.vo.UserPublicVO;

public interface UserService {

    UserPublicVO getUserPublicInfo(Integer userId);
    IPage<UserPublicVO> getUserPublicInfoList(String nickname, Integer pageNum, Integer pageSize);
    void login(String username, String password, String verifyCode);
    void logout();
    void signUp(String username, String password, String verifyCode);
    void updateUserInfo(String description, String phone, String email);

}
