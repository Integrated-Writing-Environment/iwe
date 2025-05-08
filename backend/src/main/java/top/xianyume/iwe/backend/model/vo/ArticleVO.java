package top.xianyume.iwe.backend.model.vo;

import cn.hutool.json.JSON;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Xianyume
 * @date 2025/05/08 15:47
 **/
@Data
public class ArticleVO {

    private Integer id;
    private String title;
    private String content;
    private JSON tools;
    private Integer userId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}

