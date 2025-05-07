package top.xianyume.iwe.backend.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * @author Xianyume
 * @date 2025/05/06 21:24
 **/
@Data
public class PermissionUpdateDTO {

    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer id;
    private String permission;

}

