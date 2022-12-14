package cn.icatw.service;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.User;
import cn.icatw.domain.vo.AdminUserInfoVo;

/**
 * @author icatw
 * @date 2022/10/25
 * @email 762188827@qq.com
 * @apiNote
 */
public interface LoginService {
    /**
     * 登录
     *
     * @param user user
     * @return {@link ResponseResult}
     */
    ResponseResult login(User user);

    /**
     * 注销
     *
     * @return {@link ResponseResult}
     */
    ResponseResult logout();

    /**
     * 得到信息
     *
     * @param userId 用户id
     * @return {@link AdminUserInfoVo}
     */
    AdminUserInfoVo getInfo(Long userId);

}
