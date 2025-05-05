package top.xianyume.iwe.backend.controller;

import cn.dev33.satoken.util.SaResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import top.xianyume.iwe.backend.model.dto.ToolCreateDTO;
import top.xianyume.iwe.backend.model.entity.Tool;
import top.xianyume.iwe.backend.service.intf.ToolService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ToolControllerTest {

    @Mock
    private ToolService toolService;

    @InjectMocks
    private ToolController toolController;

    private Tool testTool;
    private ToolCreateDTO toolCreateDTO;

    @BeforeEach
    void setUp() {
        testTool = new Tool();
        testTool.setId(1);
        testTool.setNickname("Test Tool");
        testTool.setDescription("Test Description");
        testTool.setFc("test-fc");

        toolCreateDTO = new ToolCreateDTO();
        toolCreateDTO.setNickname("New Tool");
        toolCreateDTO.setDescription("New Description");
        toolCreateDTO.setFc("new-fc");

    }

    @Test
    void getToolInfo_ShouldReturnTool_WhenIdExists() {
        when(toolService.getToolById(1)).thenReturn(testTool);

        SaResult result = toolController.getToolInfo(1);

        assertEquals(200, result.getCode());
        assertEquals("获取工具成功", result.getMsg());
        assertNotNull(result.getData());
        assertEquals(testTool, result.getData());
        verify(toolService, times(1)).getToolById(1);
    }

    @Test
    void createTool_ShouldReturnSuccess_WithValidInput() {
        SaResult result = toolController.createTool(toolCreateDTO);

        assertEquals(200, result.getCode());
        assertEquals("创建工具成功", result.getMsg());
        verify(toolService, times(1)).createTool("New Tool", "New Description", "new-fc");
    }

    @Test
    void deleteTool_ShouldReturnSuccess_WhenIdExists() {
        SaResult result = toolController.deleteTool(1);

        assertEquals(200, result.getCode());
        assertEquals("删除小工具成功", result.getMsg());
        verify(toolService, times(1)).deleteTool(1);
    }
}