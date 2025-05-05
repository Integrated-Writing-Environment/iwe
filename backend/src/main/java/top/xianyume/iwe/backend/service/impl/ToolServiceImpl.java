package top.xianyume.iwe.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import top.xianyume.iwe.backend.mapper.ToolMapper;
import top.xianyume.iwe.backend.model.entity.Tool;
import top.xianyume.iwe.backend.service.intf.ToolService;

@SuppressWarnings("StatementWithEmptyBody")
@Service
public class ToolServiceImpl implements ToolService {

    private final ToolMapper toolMapper;

    public ToolServiceImpl(ToolMapper toolMapper) {
        this.toolMapper = toolMapper;
    }

    @Override
    public Tool getToolById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("小工具ID不能为空");
        }
        Tool tool = toolMapper.selectOne(new QueryWrapper<Tool>()
                .select("id", "nickname", "description", "fc", "create_time")
                .eq("id", id));
        if (tool == null) {
            throw new RuntimeException("小工具不存在");
        }

        return tool;
    }

    @Override
    public Tool getToolByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("小工具名称不能为空");
        }
        Tool tool = toolMapper.selectOne(new QueryWrapper<Tool>()
                .select("id", "nickname", "description", "fc", "create_time")
                .eq("nickname", name));
        if (tool == null) {
            throw new RuntimeException("小工具不存在");
        }

        return tool;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public IPage<Tool> getToolList(String name, Integer pageNum, Integer pageSize) {

        Page<Tool> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Tool> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "nickname", "description", "fc","create_time")
                .like("nickname", name)
                .orderByDesc("create_time");

        IPage<Tool> toolPage = toolMapper.selectPage(page, queryWrapper);

        return toolPage;
    }

    @Override
    public void createTool(String name, String description, String fc) {
        if (name == null || description == null || fc == null) {
            throw new IllegalArgumentException("小工具名称、描述和功能不能为空");
        }

        if (toolMapper.selectCount(new QueryWrapper<Tool>().eq("nickname", name)) > 0) {
            throw new RuntimeException("小工具名称已存在");
        }

        Tool tool = new Tool();
        tool.setNickname(name);
        tool.setDescription(description);
        tool.setFc(fc);

        toolMapper.insert(tool);
    }

    @Override
    public void deleteTool(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("小工具ID不能为空");
        }
        toolMapper.deleteById(id);
    }
}
