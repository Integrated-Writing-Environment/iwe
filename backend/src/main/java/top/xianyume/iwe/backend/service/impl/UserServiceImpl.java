package top.xianyume.iwe.backend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import org.springframework.stereotype.Service;
import top.xianyume.iwe.backend.mapper.UserMapper;
import top.xianyume.iwe.backend.model.dto.UserLoginDTO;
import top.xianyume.iwe.backend.model.dto.UserUpdateDTO;
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
    public Boolean login(UserLoginDTO user, String password) {
        User userFromDb = userMapper.selectOne(new QueryWrapper<User>()
                .eq("uk_username", user.getUsername())
        );
        if (userFromDb == null) {
            return false;
        }
        return userFromDb.getPassword().equals(password);
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public void sign(UserLoginDTO user) {

    }

    @Override
    public void update(UserUpdateDTO user) {

    }

    @Override
    public void updatePassword(Integer id, String newPassword) {

    }

    @Override
    public void updateAvatar(Integer id, String avatar) {

    }

    @Override
    public UserInfoVO infoById(Integer id) {
        User userFromDb = userMapper.selectOne(new QueryWrapper<User>()
                .eq("pk_id", id)
        );
        UserInfoVO userInfo = new UserInfoVO();
        BeanUtil.copyProperties(userFromDb, userInfo);
        return userInfo;
    }

    @Override
    public UserInfoVO infoByUsername(String username) {
        User userFromDb = userMapper.selectOne(new QueryWrapper<User>()
                .eq("uk_username", username)
        );
        UserInfoVO userInfo = new UserInfoVO();
        BeanUtil.copyProperties(userFromDb, userInfo);
        return userInfo;
    }

    @Override
    public List<UserInfoVO> page(UserInfoVO user, int offset, int size) {
        return List.of();
    }
}
