package top.xianyume.iwe.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Xianyume
 * @date 2025/05/05 21:37
 **/
@Data
@TableName("user_article")
public class UserArticleLink {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer articleId;

    @TableLogic
    @TableField("is_deleted")
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}

