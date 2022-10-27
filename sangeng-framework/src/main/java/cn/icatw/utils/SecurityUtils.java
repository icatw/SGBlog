package cn.icatw.utils;

import cn.icatw.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author 三更  B站： https://space.bilibili.com/663528522
 */
public class SecurityUtils {

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        Object principal = getAuthentication().getPrincipal();
        //需要登陆之后才能返回
        //if ("anonymousUser".equals(principal)) {
        //    throw new SystemException(AppHttpCodeEnum.NEED_LOGIN);
        //}
        return (LoginUser) principal;
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin() {
        Long id = getLoginUser().getUser().getId();
        return id != null && 1L == id;
    }

    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }
}
