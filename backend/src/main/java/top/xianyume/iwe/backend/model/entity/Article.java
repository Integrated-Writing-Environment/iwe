package top.xianyume.iwe.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Xianyume
 * @date 2025/04/30 14:47
 **/
@Data
@TableName("article")
public class Article {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotBlank(message = "文章标题不能为空")
    @Size(max = 128, message = "文章标题长度必须小于等于于 128 个字符")
    private String title;
    // 可为空
    @Size(max = 65535, message = "文章内容长度必须少于 65535 个字符")
    private String content;
    // 可为空
    private String tools;
    @TableLogic
    @TableField("is_deleted")
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}