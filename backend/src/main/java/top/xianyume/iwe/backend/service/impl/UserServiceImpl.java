package top.xianyume.iwe.backend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import top.xianyume.iwe.backend.context.UserContext;
import top.xianyume.iwe.backend.mapper.PermissionMapper;
import top.xianyume.iwe.backend.mapper.UserMapper;
import top.xianyume.iwe.backend.mapper.UserPermissionLinkMapper;
import top.xianyume.iwe.backend.model.entity.Permission;
import top.xianyume.iwe.backend.model.entity.User;
import top.xianyume.iwe.backend.model.entity.UserPermissionLink;
import top.xianyume.iwe.backend.model.vo.UserPublicVO;
import top.xianyume.iwe.backend.service.intf.UserService;

import static cn.hutool.crypto.SecureUtil.md5;

@SuppressWarnings("StatementWithEmptyBody")
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserPermissionLinkMapper userPermissionLinkMapper;
    private final PermissionMapper permissionMapper;

    public UserServiceImpl(UserMapper userMapper, UserPermissionLinkMapper userPermissionLinkMapper, PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.userPermissionLinkMapper = userPermissionLinkMapper;
        this.permissionMapper = permissionMapper;
    }


    @Override
    public UserPublicVO getUserPublicInfo(Integer userId) {

        if (userId == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }

        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id", "nickname", "description", "create_time")
                .eq("id", userId));
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        UserPublicVO vo = new UserPublicVO();
        vo.setId(user.getId());
        vo.setNickname(user.getNickname());
        vo.setDescription(user.getDescription());
        vo.setCreateTime(user.getCreateTime());

        return vo;
    }

    @Override
    public IPage<UserPublicVO> getUserPublicInfoList(String nickname, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            pageNum = 1;
            pageSize = 20;
        }

        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "nickname", "description", "create_time");
        if (nickname != null) {
            queryWrapper.like("nickname", nickname);
        }
        queryWrapper.orderByDesc("create_time");

        IPage<User> userPage = userMapper.selectPage(page, queryWrapper);
        return userPage.convert(user -> {
            UserPublicVO vo = new UserPublicVO();
            vo.setId(user.getId());
            vo.setNickname(user.getNickname());
            vo.setDescription(user.getDescription());
            vo.setCreateTime(user.getCreateTime());
            return vo;
        });
    }


    @Override
    public void login(String username, String password, String verifyCode) {
        if (username == null || password == null || verifyCode == null) {
            throw new IllegalArgumentException("用户名、密码和验证码不能为空");
        }

        // 验证验证码
        // TODO: 实现验证码验证逻辑

        // 验证用户名和密码
        User user = userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", username));
        // 用户不存在
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 用户密码错误
        if (!md5(password).equals(user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 登录成功
        user.setPassword(null);
        StpUtil.login(user.getId());
        UserContext.setCurrentUser(user);
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public void signUp(String username, String password, String verifyCode) {
        if (username == null || password == null || verifyCode == null) {
            throw new IllegalArgumentException("用户名、密码和验证码不能为空");
        }

        // 验证验证码
        // TODO: 实现验证码验证逻辑

        User user = userMapper.selectOne(new QueryWrapper<User>()
                .select("id")
                .eq("username", username));

        // 用户已存在
        if (user != null) {
            throw new RuntimeException("用户账号已存在");
        }

        user = new User();
        user.setUsername(username);
        user.setPassword(md5(password));
        user.setNickname(RandomUtil.randomString(10));

        userMapper.insert(user);


        Permission permission = new Permission();
        permissionMapper.insert(permission);

        UserPermissionLink userPermissionLink = new UserPermissionLink();
        userPermissionLink.setUserId(user.getId());
        userPermissionLink.setPermissionId(permission.getId());

        userPermissionLinkMapper.insert(userPermissionLink);
    }

    @Override
    public void updateUserInfo(String nickname, String description, String email) {
        Integer userId = StpUtil.getLoginIdAsInt();
        User user = userMapper.selectById(userId);

        if (nickname == null) {
            throw new IllegalArgumentException("昵称长度必须在 4 到 32 个字符之间");
        } else if (nickname.isBlank()) {
            throw new IllegalArgumentException("昵称长度必须在 4 到 32 个字符之间");
        } else if (user.getNickname().equals(nickname)) {

        } else if (nickname.length() >= 32 || nickname.length() <= 4) {
            throw new IllegalArgumentException("昵称长度必须在 4 到 32 个字符之间");
        } else if (userMapper.exists(new QueryWrapper<User>().eq("nickname", nickname))){
            throw new RuntimeException("昵称已存在");
        } else {
            user.setNickname(nickname);
        }

        if (description == null) {

        } else if (description.isBlank()) {

        } else if (description.length() >= 255) {
            throw new IllegalArgumentException("昵称长度必须小于 255 个字符");
        } else {
            user.setDescription(description);
        }

        if (email == null) {

        } else if (email.isBlank()) {

        } else if (email.length() >= 64) {
            throw new IllegalArgumentException("邮箱长度必须小于 64 个字符");
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("邮箱格式不正确");
        } else {
            user.setEmail(email);
        }

        userMapper.updateById(user);
    }
}
