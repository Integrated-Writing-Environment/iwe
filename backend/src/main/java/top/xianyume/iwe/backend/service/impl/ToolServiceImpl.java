package top.xianyume.iwe.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import top.xianyume.iwe.backend.mapper.ToolMapper;
import top.xianyume.iwe.backend.model.entity.Tool;
import top.xianyume.iwe.backend.model.vo.ToolInfoVO;
import top.xianyume.iwe.backend.service.intf.ToolService;

/**
 * @author Xianyume
 * @date 2025/05/06 22:11
 **/
@Service
public class ToolServiceImpl implements ToolService {
    private final ToolMapper toolMapper;

    public ToolServiceImpl(ToolMapper toolMapper) {
        this.toolMapper = toolMapper;
    }

    @Override
    public ToolInfoVO getToolInfo(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("小工具 ID 为空");
        }
        Tool tool = toolMapper.selectOne(new QueryWrapper<Tool>()
                .select("id", "nickname", "description", "fc", "create_time")
                .eq("id", id));
        if (tool == null) {
            throw new RuntimeException("小工具不存在");
        }
        ToolInfoVO toolInfoVO = new ToolInfoVO();
        toolInfoVO.setId(tool.getId());
        toolInfoVO.setNickname(tool.getNickname());
        toolInfoVO.setDescription(tool.getDescription());
        toolInfoVO.setFc(tool.getFc());
        toolInfoVO.setCreateTime(tool.getCreateTime());

        return toolInfoVO;
    }

    @Override
    public IPage<ToolInfoVO> getToolInfoList(String nickname, Integer pageNum, Integer pageSize) {

        Page<Tool> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Tool> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "nickname", "description", "fc","create_time");
        queryWrapper.like("nickname", nickname);
        queryWrapper.orderByDesc("create_time");

        IPage<Tool> toolPage = toolMapper.selectPage(page, queryWrapper);
        return toolPage.convert(tool -> {
            ToolInfoVO vo = new ToolInfoVO();
            vo.setId(tool.getId());
            vo.setFc(tool.getFc());
            vo.setNickname(tool.getNickname());
            vo.setDescription(tool.getDescription());
            vo.setCreateTime(tool.getCreateTime());
            return vo;
        });
    }

    @Override
    public void createTool(String nickname, String description, String fc) {
        Tool tool = new Tool();
        tool.setNickname(nickname);
        tool.setDescription(description);
        tool.setFc(fc);

        toolMapper.insert(tool);
    }

    @Override
    public void updateTool(Integer id, String nickname, String description, String fc) {
        Tool tool = new Tool();
        tool.setId(id);
        tool.setNickname(nickname);
        tool.setDescription(description);
        tool.setFc(fc);

        toolMapper.updateById(tool);
    }

    @Override
    public void deleteTool(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("小工具 ID 为空");
        }
        toolMapper.deleteById(id);
    }
}

