package top.xianyume.iwe.backend.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;
import top.xianyume.iwe.backend.mapper.PermissionMapper;
import top.xianyume.iwe.backend.mapper.UserPermissionLinkMapper;
import top.xianyume.iwe.backend.model.dto.PermissionDTO;
import top.xianyume.iwe.backend.model.entity.UserPermissionLink;

import java.util.List;

/**
 * 鉴权业务
 * @author Xianyume
 * @date 2025/05/06 19:40
 **/
@Component
public class StpInterfaceImpl implements StpInterface {

    private final PermissionMapper permissionMapper;
    private final UserPermissionLinkMapper userPermissionLinkMapper;

    public StpInterfaceImpl(PermissionMapper permissionMapper, UserPermissionLinkMapper userPermissionLinkMapper) {
        this.permissionMapper = permissionMapper;
        this.userPermissionLinkMapper = userPermissionLinkMapper;
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Integer userId = Integer.valueOf((String) loginId);

        QueryWrapper<UserPermissionLink> wrapper = new QueryWrapper<UserPermissionLink>()
                .select("permission_id")
                .eq("user_id", userId);
        Integer permissionId = userPermissionLinkMapper.selectOne(wrapper).getPermissionId();
        String permissionFromDb = permissionMapper.selectById(permissionId).getPermission();

        PermissionDTO permissionDTO = PermissionDTO.fromJson(permissionFromDb);

        return permissionDTO.getPermissionList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Integer userId = Integer.valueOf((String) loginId);

        QueryWrapper<UserPermissionLink> wrapper = new QueryWrapper<UserPermissionLink>()
                .select("permission_id")
                .eq("user_id", userId);
        Integer permissionId = userPermissionLinkMapper.selectOne(wrapper).getPermissionId();
        String permissionFromDb = permissionMapper.selectById(permissionId).getPermission();

        PermissionDTO permissionDTO = PermissionDTO.fromJson(permissionFromDb);

        return permissionDTO.getRoleList();
    }
}
