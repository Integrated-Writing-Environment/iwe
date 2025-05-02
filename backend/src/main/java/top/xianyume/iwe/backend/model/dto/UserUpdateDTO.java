package top.xianyume.iwe.backend.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateDTO implements Serializable {
    @NotBlank(message = "昵称不能为空")
    @Size(min = 4, max = 16, message = "昵称长度必须在 4 到 12 个字符之间")
    private String nickname;
    @Size(min = 4, max = 128, message = "昵称长度必须在 4 到 128 个字符之间")
    private String description;
    @Max(value = 2, message = "性别参数校验失败")
    private Integer gender;
}
