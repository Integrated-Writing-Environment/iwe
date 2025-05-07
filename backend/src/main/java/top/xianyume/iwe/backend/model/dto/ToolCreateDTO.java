package top.xianyume.iwe.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Xianyume
 * @date 2025/05/06 21:59
 **/
@Data
public class ToolCreateDTO {

    @NotBlank(message = "名称不能为空")
    @Size(max = 128, message = "昵称长度不能超过 128 个字符")
    private String nickname;
    @Size(max = 255, message = "描述长度不能超过 255 个字符")
    private String description;
    @NotBlank(message = "功能不能为空")
    @Size(max = 2048, message = "功能长度不能超过 2048 个字符")
    private String fc;

}

