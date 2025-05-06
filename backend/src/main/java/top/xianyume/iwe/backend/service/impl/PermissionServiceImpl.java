package top.xianyume.iwe.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import top.xianyume.iwe.backend.mapper.PermissionMapper;
import top.xianyume.iwe.backend.mapper.UserPermissionLinkMapper;
import top.xianyume.iwe.backend.model.entity.Permission;
import top.xianyume.iwe.backend.model.entity.UserPermissionLink;
import top.xianyume.iwe.backend.service.intf.PermissionService;

/**
 * @author Xianyume
 * @date 2025/05/06 21:34
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionMapper permissionMapper;
    private final UserPermissionLinkMapper userPermissionLinkMapper;

    public PermissionServiceImpl(PermissionMapper permissionMapper, UserPermissionLinkMapper userPermissionLinkMapper) {
        this.permissionMapper = permissionMapper;
        this.userPermissionLinkMapper = userPermissionLinkMapper;
    }

    @Override
    public String getPermissionInfo(Integer id) {
        QueryWrapper<UserPermissionLink> wrapper1 = new QueryWrapper<>();
        wrapper1.select("permission_id");
        wrapper1.eq("user_id", id);

        UserPermissionLink userPermissionLink = userPermissionLinkMapper.selectOne(wrapper1);
        QueryWrapper<Permission> wrapper2 = new QueryWrapper<>();
        wrapper2.select("permission");
        wrapper2.eq("id", userPermissionLink.getPermissionId());

        return permissionMapper.selectOne(wrapper2).getPermission();
    }

    @Override
    public void updatePermission(Integer id, String permission) {
        QueryWrapper<UserPermissionLink> wrapper1 = new QueryWrapper<>();
        wrapper1.select("permission_id");
        wrapper1.eq("user_id", id);

        UserPermissionLink userPermissionLink = userPermissionLinkMapper.selectOne(wrapper1);
        Permission permissionToDb = new Permission();
        permissionToDb.setId(userPermissionLink.getPermissionId());
        permissionToDb.setPermission(permission);

        permissionMapper.updateById(permissionToDb);
    }
}

