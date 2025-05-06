package top.xianyume.iwe.backend.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Xianyume
 * @date 2025/05/05 23:24
 **/
@Data
public class ArticleContentDTO {
    @NotNull(message = "文章 id 不能为空")
    @Min(1)
    @Max(Integer.MAX_VALUE)
    private Integer id;
    @Size(max = 65535, message = "文章内容不可以超过 65535 个字符")
    private String content;
}

