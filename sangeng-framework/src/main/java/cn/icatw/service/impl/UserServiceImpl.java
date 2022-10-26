package cn.icatw.service.impl;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.LoginUser;
import cn.icatw.domain.entity.User;
import cn.icatw.domain.vo.UserInfoVo;
import cn.icatw.mapper.UserMapper;
import cn.icatw.service.UserService;
import cn.icatw.utils.BeanCopyUtils;
import cn.icatw.utils.SecurityUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author icatw
 * @since 2022-10-25 19:59:43
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 获取用户信息
     *
     * @return {@link ResponseResult}
     */
    @Override
    public ResponseResult getUserInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }
}

