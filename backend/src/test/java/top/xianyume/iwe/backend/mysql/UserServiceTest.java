package top.xianyume.iwe.backend.mysql;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import top.xianyume.iwe.backend.mapper.UserMapper;
import top.xianyume.iwe.backend.model.entity.User;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 启用事务，测试完成后自动回滚
public class UserServiceTest {

    @Autowired
    private UserMapper userMapper;

    private User testUser;

    @BeforeEach
    public void setUp() {
        // 在每个测试方法执行前创建测试用户
        testUser = new User();
        testUser.setUsername("testUser");
        testUser.setNickname("Test User");
        testUser.setPassword("password123");
        userMapper.insert(testUser);
    }

    @AfterEach
    public void tearDown() {
        // 在每个测试方法执行后清理数据
        if (testUser != null && testUser.getId() != null) {
            userMapper.deleteById(testUser.getId());
        }
    }

    /**
     * 测试新增用户
     */
    @Test
    public void testInsertUser() {
        User newUser = new User();
        newUser.setUsername("newUser");
        newUser.setNickname("New User");
        newUser.setPassword("newPassword");

        int result = userMapper.insert(newUser);
        assertEquals(1, result);
        assertNotNull(newUser.getId());

        // 验证数据是否正确插入
        User insertedUser = userMapper.selectById(newUser.getId());
        assertEquals("newUser", insertedUser.getUsername());
        assertEquals("New User", insertedUser.getNickname());
    }

    /**
     * 测试根据ID查询用户
     */
    @Test
    public void testSelectUserById() {
        User user = userMapper.selectById(testUser.getId());
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
        assertEquals("Test User", user.getNickname());
    }

    /**
     * 测试更新用户信息
     */
    @Test
    public void testUpdateUser() {
        testUser.setNickname("Updated User");
        testUser.setDescription("Updated description");
        
        int result = userMapper.updateById(testUser);
        assertEquals(1, result);

        // 验证更新是否成功
        User updatedUser = userMapper.selectById(testUser.getId());
        assertEquals("Updated User", updatedUser.getNickname());
        assertEquals("Updated description", updatedUser.getDescription());
    }

    /**
     * 测试删除用户
     */
    @Test
    public void testDeleteUser() {
        int result = userMapper.deleteById(testUser.getId());
        assertEquals(1, result);

        // 验证是否已删除
        User deletedUser = userMapper.selectById(testUser.getId());
        assertNull(deletedUser);
    }

    /**
     * 测试批量插入用户
     */
    @Test
    public void testBatchInsertUsers() {
        List<User> users = Arrays.asList(
            createUser("user1", "User 1"),
            createUser("user2", "User 2"),
            createUser("user3", "User 3")
        );

        // 批量插入
        int result = 0;
        for (User user : users) {
            result += userMapper.insert(user);
        }
        assertEquals(3, result);

        // 验证批量插入是否成功
        users.forEach(user -> {
            User insertedUser = userMapper.selectById(user.getId());
            assertNotNull(insertedUser);
        });
    }

    /**
     * 测试批量更新用户
     */
    @Test
    public void testBatchUpdateUsers() {
        // 先插入几个测试用户
        List<User> users = Arrays.asList(
            createUser("user1", "User 1"),
            createUser("user2", "User 2")
        );
        users.forEach(userMapper::insert);

        // 更新这些用户
        users.forEach(user -> {
            user.setNickname(user.getNickname() + " Updated");
            userMapper.updateById(user);
        });

        // 验证更新是否成功
        users.forEach(user -> {
            User updatedUser = userMapper.selectById(user.getId());
            assertTrue(updatedUser.getNickname().endsWith("Updated"));
        });
    }

    /**
     * 测试批量删除用户
     */
    @Test
    public void testBatchDeleteUsers() {
        // 先插入几个测试用户
        List<User> users = Arrays.asList(
            createUser("user1", "User 1"),
            createUser("user2", "User 2")
        );
        users.forEach(userMapper::insert);

        // 批量删除
        users.forEach(user -> userMapper.deleteById(user.getId()));

        // 验证是否已删除
        users.forEach(user -> {
            User deletedUser = userMapper.selectById(user.getId());
            assertNull(deletedUser);
        });
    }

    /**
     * 辅助方法：创建用户对象
     */
    private User createUser(String username, String nickname) {
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword("password123");
        return user;
    }
}
