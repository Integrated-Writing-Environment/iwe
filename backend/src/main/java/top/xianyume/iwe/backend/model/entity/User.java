package top.xianyume.iwe.backend.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User{
    @TableId("pk_id")
    private Integer id;
    @TableField("uk_username")
    private String username;
    @TableField("uk_nickname")
    private String nickname;
}
