package top.xianyume.iwe.backend.model.vo;

import lombok.Data;

@Data
public class UserProfileVO {

    private Integer id;
    private String username;
    private String nickname;
    private String description;
    private String phone;
    private String email;
    private String createTime;

}
