package top.xianyume.iwe.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Xianyume
 * @date 2025/05/05 13:42
 **/
@Data
public class ToolCreateDTO {

    @NotBlank(message = "小工具名称不能为空")
    @Size(min = 2, max = 128, message = "小工具名称必须在 2 到 128 个字符之间")
    private String nickname;
    @NotBlank(message = "小工具简介不能为空")
    @Size(max = 255, message = "小工具简介必须小于 255 个字符")
    private String description;
    @NotBlank(message = "小工具函数不能为空")
    @Size(max = 2048, message = "小工具函数必须小于 2048 个字符")
    private String fc;
}

