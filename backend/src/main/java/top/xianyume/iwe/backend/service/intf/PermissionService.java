package top.xianyume.iwe.backend.service.intf;

public interface PermissionService {
    String getPermissionInfo(Integer id);
    void updatePermission(Integer id, String permission);
}
