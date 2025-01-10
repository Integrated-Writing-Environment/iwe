package top.xianyume.iwe.backend.model.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInfoVO {
    private Integer id;
    private String nickname;
    private String avatar;
    private String description;
    private String gender;
    private String createTime;
}
