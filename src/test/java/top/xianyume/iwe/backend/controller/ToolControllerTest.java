package top.xianyume.iwe.backend.controller;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import top.xianyume.iwe.backend.model.dto.ToolCreateDTO;
import top.xianyume.iwe.backend.model.dto.ToolQueryDTO;
import top.xianyume.iwe.backend.model.entity.Tool;
import top.xianyume.iwe.backend.service.intf.ToolService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ToolControllerTest {

    @Mock
    private ToolService toolService;

    @InjectMocks
    private ToolController toolController;

    private Tool testTool;
    private IPage<Tool> testPage;

    @Before
    public void setUp() {
        testTool = new Tool();
        testTool.setId(1);
        testTool.setNickname("testTool");

        testPage = mock(IPage.class);
    }

    @Test
    public void testGetToolInfo_Success() {
        when(toolService.getToolById(1)).thenReturn(testTool);

        SaResult result = toolController.getToolInfo(1);

        assertEquals("获取工具成功", result.getMsg());
        assertEquals(testTool, result.getData());
        verify(toolService).getToolById(1);
    }

    @Test
    public void testGetToolInfoList_Success() {
        ToolQueryDTO queryDTO = new ToolQueryDTO();
        queryDTO.setNickname("test");

        when(toolService.getToolList("test", 1, 10)).thenReturn(testPage);

        SaResult result = toolController.getToolInfoList(queryDTO);

        assertEquals("获取工具列表成功", result.getMsg());
        assertEquals(testPage, result.getData());
        verify(toolService).getToolList("test", 1, 10);
    }

    @Test
    public void testCreateTool_Success() {
        ToolCreateDTO createDTO = new ToolCreateDTO();
        createDTO.setNickname("newTool");
        createDTO.setDescription("desc");
        createDTO.setFc("fc");

        SaResult result = toolController.createTool(createDTO);

        assertEquals("创建工具成功", result.getMsg());
        verify(toolService).createTool("newTool", "desc", "fc");
    }

    @Test
    public void testDeleteTool_Success() {
        SaResult result = toolController.deleteTool(1);

        assertEquals("删除小工具成功", result.getMsg());
        verify(toolService).deleteTool(1);
    }
}