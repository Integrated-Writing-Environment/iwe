package top.xianyume.iwe.backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    private String createTime;
    private String updateTime;

}
