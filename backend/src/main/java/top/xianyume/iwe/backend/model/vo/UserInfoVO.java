package top.xianyume.iwe.backend.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO {
    private Integer id;
    private String username;
    private String nickname;
    private String avatar;
    private String description;
    private String gender;
    private String createTime;
}
