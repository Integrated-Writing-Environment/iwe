package top.xianyume.iwe.backend.controller;

import cn.dev33.satoken.annotation.SaCheckOr;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.xianyume.iwe.backend.model.dto.ToolCreateDTO;
import top.xianyume.iwe.backend.model.vo.ToolInfoVO;
import top.xianyume.iwe.backend.service.intf.ToolService;

/**
 * @author Xianyume
 * @date 2025/05/06 22:03
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
    @SaCheckOr(
            role = @SaCheckRole("user"),
            permission = @SaCheckPermission("tool.get")
    )
    public SaResult getToolInfo(@PathVariable Integer id) {
        ToolInfoVO tool = toolService.getToolInfo(id);
        return SaResult.ok("获取单个工具成功")
                .setData(tool);
    }

    @GetMapping
    @SaCheckOr(
            role = @SaCheckRole("user"),
            permission = @SaCheckPermission("tool.get_list")
    )
    public SaResult getToolInfoList(
            @RequestParam(required = false) String toolName,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        IPage<ToolInfoVO> toolList = toolService.getToolInfoList(toolName, pageNum, pageSize);
        return SaResult.ok("获取多个工具成功")
               .setData(toolList);
    }

    @PostMapping
    @SaCheckOr(
            role = @SaCheckRole("user"),
            permission = @SaCheckPermission("tool.create")
    )
    public SaResult createTool(@ModelAttribute @Valid ToolCreateDTO tool) {
        toolService.createTool(tool.getNickname(), tool.getDescription(), tool.getFc());
        return SaResult.ok("创建小工具成功");
    }

    @DeleteMapping
    @SaCheckOr(
            role = @SaCheckRole("admin"),
            permission = @SaCheckPermission("tool.delete")
    )
    public SaResult deleteTool(@RequestParam Integer id) {
        toolService.deleteTool(id);
        return SaResult.ok("删除小工具成功");
    }
}

