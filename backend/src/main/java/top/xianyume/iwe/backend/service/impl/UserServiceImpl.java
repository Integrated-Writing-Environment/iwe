package top.xianyume.iwe.backend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import top.xianyume.iwe.backend.mapper.UserMapper;
import top.xianyume.iwe.backend.model.vo.UserPublicVO;
import top.xianyume.iwe.backend.service.intf.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public UserPublicVO getUserPublicInfo(Integer userId) {
        
        return null;
    }

    @Override
    public IPage<UserPublicVO> getUserPublicInfoList(String username, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public void login(String username, String password, String verifyCode) {

    }

    @Override
    public void logout() {

    }

    @Override
    public void signUp(String username, String password, String verifyCode) {

    }

    @Override
    public void updateUserInfo(String description, String phone, String email) {

    }
}
