package cn.icatw.service.impl;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.User;
import cn.icatw.domain.vo.UserInfoVo;
import cn.icatw.enums.AppHttpCodeEnum;
import cn.icatw.mapper.UserMapper;
import cn.icatw.service.UserService;
import cn.icatw.utils.BeanCopyUtils;
import cn.icatw.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户表(User)表服务实现类
 *
 * @author icatw
 * @since 2022-10-25 19:59:43
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    PasswordEncoder passwordEncoder;

    /**
     * 获取用户信息
     *
     * @return {@link ResponseResult}
     */
    @Override
    public ResponseResult getUserInfo() {
        //获取当前用户id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        boolean flag = getUserByUsername(user);
        if (flag) {
            updateById(user);
            return ResponseResult.okResult();
        }
        //    该用户名已存在
        return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST);

    }

    @Override
    public ResponseResult register(User user) {
        //用户名唯一 查询数据库是否存在 若存在 抛出异常提示信息
        boolean flag = getUserByUsername(user);
        if (flag) {
            //    不存在，密码加密，添加数据库
            String encodePass = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodePass);

            user.setSex("0");
            save(user);
            return ResponseResult.okResult();
        }
        //    该用户名已存在
        return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST);
    }

    /**
     * 查找用户名是否已经存在（用户名唯一）
     *
     * @param user 用户
     * @return boolean 用户名存在返回false，不存在返回true
     */
    private boolean getUserByUsername(User user) {
        //查找用户名是否存在
        int count = count(new LambdaQueryWrapper<User>().eq(User::getUserName, user.getUserName()));
        return count <= 0;
    }
}

