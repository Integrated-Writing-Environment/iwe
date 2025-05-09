package top.xianyume.iwe.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Xianyume
 * @date 2025/05/05 19:26
 **/
@Data
public class ArticleDTO {

    private Integer id;
    @NotBlank(message = "标题不能为空")
    @Size(min = 1, max = 128, message = "标题长度必须在 1 到 128 之间")
    private String title;
    @Size(max = 65538, message = "内容长度必须在 65538 以下")
    private String content;
    private String tools;

    private Integer userId;
}

