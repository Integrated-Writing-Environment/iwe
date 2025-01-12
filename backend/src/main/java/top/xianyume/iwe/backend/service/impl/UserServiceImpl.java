package top.xianyume.iwe.backend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    }

    @Override
    public Integer sign(UserLoginDTO user) {
        String password = DigestUtil.md5Hex(user.getPassword());
        String randomString = RandomUtil.randomString(6);

        User userNew = new User();
        userNew.setUsername(user.getUsername());
        userNew.setPassword(password);
        userNew.setNickname("用户_" + randomString);
        userMapper.insert(userNew);

        User userFromDb = userMapper.selectOne(new QueryWrapper<User>()
                .eq("uk_username", user.getUsername())
        );

        return userFromDb.getId();
    }

    @Override
    public void update(UserUpdateDTO user) {
        Integer loginId = Integer.valueOf((String) StpUtil.getLoginId());

        User userNew = new User();
        BeanUtil.copyProperties(user, userNew);

        userNew.setId(loginId);

        userMapper.updateById(userNew);
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
    public UserInfoVO infoByNickname(String nickname) {
        User userFromDb = userMapper.selectOne(new QueryWrapper<User>()
                .eq("uk_nickname", nickname)
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
