package top.xianyume.iwe.backend.service.intf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.xianyume.iwe.backend.model.vo.ToolInfoVO;

public interface ToolService {

    ToolInfoVO getToolInfo(Integer id);
    IPage<ToolInfoVO> getToolInfoList(String nickname, Integer pageNum, Integer pageSize);
    void createTool(String nickname, String description, String fc);
    void updateTool(Integer id, String nickname, String description, String fc);
    void deleteTool(Integer id);

}
