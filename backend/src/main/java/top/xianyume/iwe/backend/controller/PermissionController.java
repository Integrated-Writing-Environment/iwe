package top.xianyume.iwe.backend.controller;

import cn.dev33.satoken.annotation.SaCheckOr;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.util.SaResult;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.xianyume.iwe.backend.model.dto.PermissionUpdateDTO;
import top.xianyume.iwe.backend.service.intf.PermissionService;

/**
 * @author Xianyume
 * @date 2025/05/06 21:18
 **/
@RestController
@RequestMapping("/admin")
@Validated
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 查看用户权限
     */
    @GetMapping("/{id}")
    @SaCheckOr(
            role = @SaCheckRole("admin"),
            permission = @SaCheckPermission("admin.get_permission")
    )
    public SaResult getPermissionInfo(@PathVariable Integer id) {
        String permission = permissionService.getPermissionInfo(id);
        return SaResult.ok("获取用户权限成功")
                .setData(permission);
    }

    /**
     * 修改用户权限
     */
    @PutMapping
    @SaCheckOr(
            role = @SaCheckRole("admin"),
            permission = @SaCheckPermission("admin.update_permission")
    )
    public SaResult updatePermission(@ModelAttribute @Valid PermissionUpdateDTO permission) {
        permissionService.updatePermission(permission.getId(), permission.getPermission());
        return SaResult.ok("修改用户权限成功");
    }
}

