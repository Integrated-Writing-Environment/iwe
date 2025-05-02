package top.xianyume.iwe.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User{
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 32, message = "用户名长度必须在 4 到 32 之间")
    private String username;
    @NotBlank(message = "昵称不能为空")
    @Size(min = 4, max = 32, message = "用户昵称长度必须在 4 到 20 之间")
    private String nickname;
    @TableField("pwd")
    @Size(min = 4, max = 32, message = "密码长度必须在 4 到 32 之间")
    private String password;
    // 可为空
    @Size(max = 255, message = "字符串长度必须少于 255 个字符")
    private String description;
    // 可为空
    @Size(max = 32, message = "手机号长度必须少于 32 个字符")
    private String phone;
    // 可为空
    @Size(max = 64, message = "邮箱长度必须少于 64 个字符")
    private String email;
    @TableLogic
    @TableField("is_deleted")
    private Integer deleted;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
