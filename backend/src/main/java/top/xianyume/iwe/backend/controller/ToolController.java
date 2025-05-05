package top.xianyume.iwe.backend.controller;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.xianyume.iwe.backend.model.dto.ToolCreateDTO;
import top.xianyume.iwe.backend.model.entity.Tool;
import top.xianyume.iwe.backend.service.intf.ToolService;

/**
 * @author Xianyume
 * @date 2025/05/05 13:31
 **/
@RestController
@RequestMapping("/tools")
@Validated
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping("/{id}")
    public SaResult getToolInfo(@PathVariable Integer id) {
        Tool tool = toolService.getToolById(id);
        return SaResult.ok("获取工具成功")
                .setData(tool);
    }

    @GetMapping
    public SaResult getToolInfoList(
            @RequestParam(required = false) String toolname,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        IPage<Tool> tools = toolService.getToolList(toolname, pageNum, pageSize);
        return SaResult.ok("获取工具列表成功")
                .setData(tools);
    }

    @PostMapping
    public SaResult createTool(@ModelAttribute @Valid ToolCreateDTO tool) {
        toolService.createTool(tool.getNickname(), tool.getDescription(), tool.getFc());
        return SaResult.ok("创建工具成功");
    }

    @DeleteMapping("/{id}")
    public SaResult deleteTool(@PathVariable Integer id) {
        toolService.deleteTool(id);
        return SaResult.ok("删除小工具成功");
    }
}

