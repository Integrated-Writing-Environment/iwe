package top.xianyume.iwe.backend.service.impl;

import org.springframework.stereotype.Service;
import top.xianyume.iwe.backend.context.UserContext;
import top.xianyume.iwe.backend.mapper.UserMapper;
import top.xianyume.iwe.backend.model.entity.User;
import top.xianyume.iwe.backend.service.intf.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void say(String s) {
        User user = UserContext.getCurrentUser();
        System.out.println(user);
    }
}
