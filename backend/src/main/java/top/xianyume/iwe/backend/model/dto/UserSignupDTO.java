package top.xianyume.iwe.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Xianyume
 * @date 2025/05/03 02:58
 **/
@Data
public class UserSignupDTO implements Serializable {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 32, message = "用户名长度必须在 4 到 32 之间")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "账号格式不正确，仅允许字母、数字和下划线")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 4, max = 32, message = "密码长度必须在 4 到 32 个字符之间")
    private String password;

    @NotBlank(message = "验证码不能为空")
    private String verifyCode;
}

