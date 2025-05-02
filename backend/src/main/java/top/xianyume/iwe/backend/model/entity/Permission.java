package top.xianyume.iwe.backend.model.entity;

import cn.hutool.json.JSON;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Xianyume
 * @date 2025/05/01 23:44
 **/
@Data
@TableName("permission")
public class Permission {
    @TableId(type = IdType.AUTO)
    private Integer id;
    // 可为空
    private JSON permission;
    @TableLogic
    @TableField("is_deleted")
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

