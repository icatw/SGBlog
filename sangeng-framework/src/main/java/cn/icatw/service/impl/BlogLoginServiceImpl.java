package cn.icatw.service.impl;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.LoginUser;
import cn.icatw.domain.entity.User;
import cn.icatw.domain.vo.BlogUserLoginVo;
import cn.icatw.domain.vo.UserInfoVo;
import cn.icatw.service.BlogLoginService;
import cn.icatw.utils.BeanCopyUtils;
import cn.icatw.utils.JwtUtil;
import cn.icatw.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author icatw
 * @date 2022/10/25
 * @email 762188827@qq.com
 * @apiNote
 */
@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        //判断是否认证通过
        if (Objects.isNull(authenticate)) {
            //    空 未找到 用户名或密码错误
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userId，生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long userId = loginUser.getUser().getId();
        String token = JwtUtil.createJWT(userId.toString());
        //把用户信息存入redis
        redisCache.setCacheObject(SystemConstants.LOGIN_KEY + userId, loginUser);
        //把token和userInfo封装成map返回
        BlogUserLoginVo vo = new BlogUserLoginVo();
        vo.setToken(token);
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        vo.setUserInfo(userInfoVo);
        return ResponseResult.okResult(vo);
    }
}
