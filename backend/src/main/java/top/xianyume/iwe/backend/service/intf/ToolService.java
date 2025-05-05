package top.xianyume.iwe.backend.service.intf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.xianyume.iwe.backend.model.entity.Tool;

public interface ToolService {
    Tool getToolById(Integer id);
    Tool getToolByName(String name);
    IPage<Tool> getToolList(String name, Integer pageNum, Integer pageSize);
    void createTool(String name, String description, String fc);
    void deleteTool(Integer id);
}
