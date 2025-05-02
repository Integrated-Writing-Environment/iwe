package top.xianyume.iwe.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User{

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String nickname;
    @TableField("pwd")
    private String password;
    private String description;
    private String phone;
    private String email;

    @TableLogic
    @TableField("is_deleted")
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
