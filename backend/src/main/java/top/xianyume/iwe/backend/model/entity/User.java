package top.xianyume.iwe.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User{
    @TableId(value = "pk_id",
            type = IdType.AUTO
    )
    private Integer id;
    @TableField("uk_username")
    private String username;
    @TableField("uk_nickname")
    private String nickname;
    @TableField("pwd")
    private String password;
    private String description;
    private String phone;
    private String email;
    private String idNumber;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
