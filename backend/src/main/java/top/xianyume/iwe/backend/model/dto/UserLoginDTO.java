package top.xianyume.iwe.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDTO implements Serializable {
    @NotBlank(message = "账号不能为空")
    @Size(min = 4, max = 16, message = "账号长度必须在 4 到 16 个字符之间")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "账号格式不正确，仅允许字母、数字和下划线")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Size(min = 4, max = 32, message = "密码长度必须在 4 到 32 个字符之间")
    private String password;
}
