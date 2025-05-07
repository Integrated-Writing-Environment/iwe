package top.xianyume.iwe.backend.model.dto;

import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.util.List;

/**
 * @author Xianyume
 * @date 2025/05/06 20:12
 **/
@Data
public class PermissionDTO {

    private List<String> roleList;
    private List<String> permissionList;

    /**
     * 将 PermissionDTO对象转换为JSON字符串
     * @param
     * @return
     */
    public String toJson() {
        return JSONUtil.toJsonStr(this);
    }

    /**
     * 从 JSON 字符串解析为 PermissionDTO 对象`
     * @param jsonStr
     * @return
     */
    public static PermissionDTO fromJson(String jsonStr) {
        return JSONUtil.toBean(jsonStr, PermissionDTO.class);
    }

    /**
     * 获取一个默认的 PermissionDTO 对象
     * @param
     * @return
     */
    public static PermissionDTO getUserPermissionDefault() {
        PermissionDTO permission = new PermissionDTO();
        permission.setRoleList(List.of("user"));
        permission.setPermissionList(List.of());
        return permission;
    }
}

