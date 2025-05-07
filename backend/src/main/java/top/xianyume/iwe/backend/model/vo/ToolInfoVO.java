package top.xianyume.iwe.backend.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Xianyume
 * @date 2025/05/06 22:01
 **/
@Data
public class ToolInfoVO {

    private Integer id;
    private String nickname;
    private String description;
    private String fc;
    private LocalDateTime createTime;

}

