package top.xianyume.iwe.backend.mysql;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import top.xianyume.iwe.backend.mapper.ToolMapper;
import top.xianyume.iwe.backend.model.entity.Tool;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tool实体类测试类
 * 使用@Transactional注解确保测试后数据回滚
 */
@SpringBootTest
@Transactional
public class ToolTest {

    @Autowired
    private ToolMapper toolMapper;

    private Tool testTool;

    /**
     * 在每个测试方法执行前创建测试工具
     */
    @BeforeEach
    public void setUp() {
        testTool = new Tool();
        testTool.setNickname("测试工具");
        testTool.setDescription("测试工具描述");
        toolMapper.insert(testTool);
    }

    /**
     * 在每个测试方法执行后清理数据
     */
    @AfterEach
    public void tearDown() {
        if (testTool != null && testTool.getId() != null) {
            toolMapper.deleteById(testTool.getId());
        }
    }

    /**
     * 测试新增工具
     */
    @Test
    public void testInsertTool() {
        Tool newTool = new Tool();
        newTool.setNickname("新工具");
        newTool.setDescription("新工具描述");

        int result = toolMapper.insert(newTool);
        assertEquals(1, result);
        assertNotNull(newTool.getId());

        // 验证数据是否正确插入
        Tool insertedTool = toolMapper.selectById(newTool.getId());
        assertEquals("新工具", insertedTool.getNickname());
        assertEquals("新工具描述", insertedTool.getDescription());
    }

    /**
     * 测试根据ID查询工具
     */
    @Test
    public void testSelectToolById() {
        Tool tool = toolMapper.selectById(testTool.getId());
        assertNotNull(tool);
        assertEquals("测试工具", tool.getNickname());
        assertEquals("测试工具描述", tool.getDescription());
    }

    /**
     * 测试更新工具信息
     */
    @Test
    public void testUpdateTool() {
        testTool.setNickname("更新后的工具名");
        testTool.setDescription("更新后的描述");

        int result = toolMapper.updateById(testTool);
        assertEquals(1, result);

        // 验证更新是否成功
        Tool updatedTool = toolMapper.selectById(testTool.getId());
        assertEquals("更新后的工具名", updatedTool.getNickname());
        assertEquals("更新后的描述", updatedTool.getDescription());
    }

    /**
     * 测试删除工具
     */
    @Test
    public void testDeleteTool() {
        int result = toolMapper.deleteById(testTool.getId());
        assertEquals(1, result);

        // 验证是否已删除
        Tool deletedTool = toolMapper.selectById(testTool.getId());
        assertNull(deletedTool);
    }

    /**
     * 测试批量插入工具
     */
    @Test
    public void testBatchInsertTools() {
        List<Tool> tools = Arrays.asList(
                createTool("工具1", "描述1"),
                createTool("工具2", "描述2"),
                createTool("工具3", "描述3")
        );

        // 批量插入
        int result = 0;
        for (Tool tool : tools) {
            result += toolMapper.insert(tool);
        }
        assertEquals(3, result);

        // 验证批量插入是否成功
        tools.forEach(tool -> {
            Tool insertedTool = toolMapper.selectById(tool.getId());
            assertNotNull(insertedTool);
        });
    }

    /**
     * 测试批量更新工具
     */
    @Test
    public void testBatchUpdateTools() {
        // 先插入几个测试工具
        List<Tool> tools = Arrays.asList(
                createTool("工具1", "描述1"),
                createTool("工具2", "描述2")
        );
        tools.forEach(toolMapper::insert);

        // 更新这些工具
        tools.forEach(tool -> {
            tool.setNickname(tool.getNickname() + " 更新");
            toolMapper.updateById(tool);
        });

        // 验证更新是否成功
        tools.forEach(tool -> {
            Tool updatedTool = toolMapper.selectById(tool.getId());
            assertTrue(updatedTool.getNickname().endsWith("更新"));
        });
    }

    /**
     * 测试批量删除工具
     */
    @Test
    public void testBatchDeleteTools() {
        // 先插入几个测试工具
        List<Tool> tools = Arrays.asList(
                createTool("工具1", "描述1"),
                createTool("工具2", "描述2")
        );
        tools.forEach(toolMapper::insert);

        // 批量删除
        tools.forEach(tool -> toolMapper.deleteById(tool.getId()));

        // 验证是否已删除
        tools.forEach(tool -> {
            Tool deletedTool = toolMapper.selectById(tool.getId());
            assertNull(deletedTool);
        });
    }

    /**
     * 辅助方法：创建工具对象
     */
    private Tool createTool(String nickname, String description) {
        Tool tool = new Tool();
        tool.setNickname(nickname);
        tool.setDescription(description);
        return tool;
    }
}