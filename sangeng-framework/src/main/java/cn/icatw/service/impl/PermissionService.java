package cn.icatw.service.impl;

import cn.icatw.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author icatw
 * @date 2022/10/28
 * @email 762188827@qq.com
 * @apiNote
 */
@Service("ps")
public class PermissionService {
    /**
     * 判断当前用户是否有权限
     *
     * @param permission 许可
     * @return boolean
     */
    public boolean hasPermission(String permission) {
        //如果是超级管理员 直接返回true
        if (SecurityUtils.isAdmin()) {
            return true;
        }
        //    否则，获取当前登陆用户所具有的权限列表，判断是否存在
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }
}
