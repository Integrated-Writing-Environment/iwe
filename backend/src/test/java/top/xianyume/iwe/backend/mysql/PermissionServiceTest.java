package top.xianyume.iwe.backend.mysql;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import top.xianyume.iwe.backend.mapper.PermissionMapper;
import top.xianyume.iwe.backend.model.entity.Permission;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Permission 实体测试类
 * 包含基础CRUD和批量操作测试
 */
@SpringBootTest
@Transactional // 启用事务，测试完成后自动回滚
public class PermissionServiceTest {

    @Autowired
    private PermissionMapper permissionMapper;

    private Permission testPermission; // 测试用的权限对象

    /**
     * 在每个测试方法执行前创建测试数据
     */
    @BeforeEach
    public void setUp() {
        // 创建测试权限
        testPermission = new Permission();
        testPermission.setPermission(null); // 可为空字段
        permissionMapper.insert(testPermission);
    }

    /**
     * 在每个测试方法执行后清理数据
     */
    @AfterEach
    public void tearDown() {
        if (testPermission != null && testPermission.getId() != null) {
            permissionMapper.deleteById(testPermission.getId());
        }
    }

    /**
     * 测试新增权限
     */
    @Test
    public void testInsertPermission() {
        Permission newPermission = new Permission();
        newPermission.setPermission(null);

        int result = permissionMapper.insert(newPermission);
        assertEquals(1, result);
        assertNotNull(newPermission.getId());

        // 验证数据是否正确插入
        Permission insertedPermission = permissionMapper.selectById(newPermission.getId());
        assertNull(insertedPermission.getPermission()); // 验证可为空字段
    }

    /**
     * 测试根据ID查询权限
     */
    @Test
    public void testSelectPermissionById() {
        Permission permission = permissionMapper.selectById(testPermission.getId());
        assertNotNull(permission);
        assertEquals(testPermission.getId(), permission.getId());
    }

    /**
     * 测试更新权限信息
     */
    @Test
    public void testUpdatePermission() {
        // 更新权限信息
        testPermission.setPermission(null); // 更新可为空字段

        int result = permissionMapper.updateById(testPermission);
        assertEquals(1, result);

        // 验证更新是否成功
        Permission updatedPermission = permissionMapper.selectById(testPermission.getId());
        assertNull(updatedPermission.getPermission());
    }

    /**
     * 测试删除权限
     */
    @Test
    public void testDeletePermission() {
        int result = permissionMapper.deleteById(testPermission.getId());
        assertEquals(1, result);

        // 验证是否已删除
        Permission deletedPermission = permissionMapper.selectById(testPermission.getId());
        assertNull(deletedPermission);
    }

    /**
     * 测试批量插入权限
     */
    @Test
    public void testBatchInsertPermissions() {
        List<Permission> permissions = Arrays.asList(
                createPermission(),
                createPermission(),
                createPermission()
        );

        // 批量插入
        int result = 0;
        for (Permission permission : permissions) {
            result += permissionMapper.insert(permission);
        }
        assertEquals(3, result);

        // 验证批量插入是否成功
        permissions.forEach(permission -> {
            Permission insertedPermission = permissionMapper.selectById(permission.getId());
            assertNotNull(insertedPermission);
        });
    }

    /**
     * 测试批量更新权限
     */
    @Test
    public void testBatchUpdatePermissions() {
        // 先插入几个测试权限
        List<Permission> permissions = Arrays.asList(
                createPermission(),
                createPermission()
        );
        permissions.forEach(permissionMapper::insert);

        // 更新这些权限
        permissions.forEach(permission -> {
            permission.setPermission(null);
            permissionMapper.updateById(permission);
        });

        // 验证更新是否成功
        permissions.forEach(permission -> {
            Permission updatedPermission = permissionMapper.selectById(permission.getId());
            assertNull(updatedPermission.getPermission());
        });
    }

    /**
     * 测试批量删除权限
     */
    @Test
    public void testBatchDeletePermissions() {
        // 先插入几个测试权限
        List<Permission> permissions = Arrays.asList(
                createPermission(),
                createPermission()
        );
        permissions.forEach(permissionMapper::insert);

        // 批量删除
        permissions.forEach(permission -> permissionMapper.deleteById(permission.getId()));

        // 验证是否已删除
        permissions.forEach(permission -> {
            Permission deletedPermission = permissionMapper.selectById(permission.getId());
            assertNull(deletedPermission);
        });
    }

    /**
     * 辅助方法：创建权限对象
     */
    private Permission createPermission() {
        Permission permission = new Permission();
        permission.setPermission(null);
        return permission;
    }
}