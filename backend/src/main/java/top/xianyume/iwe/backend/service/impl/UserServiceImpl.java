package top.xianyume.iwe.backend.service.impl;

import org.springframework.stereotype.Service;
import top.xianyume.iwe.backend.mapper.UserMapper;
import top.xianyume.iwe.backend.model.entity.User;
import top.xianyume.iwe.backend.model.vo.UserInfoVO;
import top.xianyume.iwe.backend.service.intf.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Boolean login(User user, String password) {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public void signUp(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void updatePassword(User user, String newPassword) {

    }

    @Override
    public UserInfoVO info(User user) {
        return null;
    }

    @Override
    public List<UserInfoVO> page(UserInfoVO user, int offset, int size) {
        return List.of();
    }
}
