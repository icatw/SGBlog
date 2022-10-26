package cn.icatw.service;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户表(User)表服务接口
 *
 * @author icatw
 * @since 2022-10-25 19:59:43
 */
public interface UserService extends IService<User> {

    /**
     * 获取用户信息
     *
     * @return {@link ResponseResult}
     */
    ResponseResult getUserInfo();

    /**
     * 更新用户信息
     *
     * @param userInfoVo 用户信息签证官
     * @return {@link ResponseResult}
     */
    ResponseResult updateUserInfo(User userInfoVo);

    /**
     * 注册
     *
     * @param user 用户
     * @return {@link ResponseResult}
     */
    ResponseResult register(User user);
}

