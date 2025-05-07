package top.xianyume.iwe.backend.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateDTO implements Serializable {

    @Size(min = 1, max = 32)
    private String nickname;
    @Size(min = 1, max = 255, message = "描述长度不能超过 32 个字符")
    private String description;
    private String email;

}
