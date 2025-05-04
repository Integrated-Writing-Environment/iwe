package top.xianyume.iwe.backend.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Xianyume
 * @date 2025/05/04 00:46
 **/
@Data
public class UserQueryDTO implements Serializable {

    private Integer id;
    @Size(min = 2, max = 32, message = "昵称长度必须在 2 或 32 个字符之间")
    private String nickname;
    private Integer pageNum;
    @Max(value = 100, message = "分页大小必须小于 100 个字符")
    private Integer pageSize;

}

