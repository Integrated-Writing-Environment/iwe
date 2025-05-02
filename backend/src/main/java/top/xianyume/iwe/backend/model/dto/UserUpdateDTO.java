package top.xianyume.iwe.backend.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateDTO implements Serializable {

    @NotBlank(message = "昵称不能为空")
    @Size(min = 4, max = 32, message = "昵称长度必须在 4 到 32 个字符之间")
    private String nickname;

    @Size(max = 255, message = "字符串长度必须少于 255 个字符")
    private String description;

    @Size(max = 64, message = "邮箱长度必须少于 64 个字符")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    private String email;

}
