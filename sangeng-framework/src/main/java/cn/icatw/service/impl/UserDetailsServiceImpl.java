package cn.icatw.service.impl;

import cn.icatw.domain.entity.LoginUser;
import cn.icatw.domain.entity.User;
import cn.icatw.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author icatw
 * @date 2022/10/25
 * @email 762188827@qq.com
 * @apiNote
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        User user = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUserName, username));
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在！");
        }
        //如果是后台用户账号，查询权限信息
        //判断是否查到用户
        //如果没查到抛出异常
        //查到用户，返回用户信息
        return new LoginUser(user);

    }
}
