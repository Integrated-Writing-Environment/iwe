package top.xianyume.iwe.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Xianyume
 * @date 2025/05/05 20:46
 **/
@Data
public class ToolCallDTO {

    private Integer id;
    @NotBlank(message = "小工具函数不能为空")
    @Size(max = 2048, message = "小工具函数不可以超过 2048 个字符")
    private String fc;
    @Size(max = 65535, message = "小工具函数的参数不可以超过 65535 个字符")
    private String originalContent;

}

