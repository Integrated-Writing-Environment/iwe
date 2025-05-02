package top.xianyume.iwe.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Xianyume
 * @date 2025/05/01 23:42
 **/
@Data
@TableName("tool")
public class Tool {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String nickname;
    private String description;
    // 可为空
    private String fc;
    @TableLogic
    @TableField("is_deleted")
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

