package top.xianyume.iwe.backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseMapper;
import top.xianyume.iwe.backend.model.entity.User;
import top.xianyume.iwe.backend.model.vo.UserPublicVO;

import java.util.List;

public interface UserMapper extends MPJBaseMapper<User> {
    List<UserPublicVO> selectPageVo(IPage<UserPublicVO> page, Integer state);
}
