package top.xianyume.iwe.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "小工具名称不能为空")
    @Size(max = 128, message = "小工具名称长度必须小于于 128 个字符")
    private String nickname;
    @Size(max = 255, message = "小工具简介长度必须少于 255 个字符")
    private String description;
    @Size(max = 2048, message = "小工具函数长度必须少于 2048 个字符")
    private String fc;
    @TableLogic
    @TableField("is_deleted")
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

