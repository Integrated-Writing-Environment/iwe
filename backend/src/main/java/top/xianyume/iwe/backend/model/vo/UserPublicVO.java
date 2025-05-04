package top.xianyume.iwe.backend.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Xianyume
 * @date 2025/05/03 03:13
 **/
@Data
public class UserPublicVO {

    private Integer id;
    private String nickname;
    private String description;

    private LocalDateTime createTime;

}

