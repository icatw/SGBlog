package cn.icatw.service.impl;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.LoginUser;
import cn.icatw.domain.entity.User;
import cn.icatw.domain.entity.UserRole;
import cn.icatw.domain.vo.AdminUserInfoVo;
import cn.icatw.domain.vo.UserInfoVo;
import cn.icatw.enums.AppHttpCodeEnum;
import cn.icatw.exception.SystemException;
import cn.icatw.service.*;
import cn.icatw.utils.BeanCopyUtils;
import cn.icatw.utils.JwtUtil;
import cn.icatw.utils.RedisCache;
import cn.icatw.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author icatw
 * @date 2022/10/27
 * @email 762188827@qq.com
 * @apiNote
 */
@Service
public class SystemLoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisCache redisCache;

    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RoleMenuService roleMenuService;

    @Override
    public ResponseResult login(User user) {
        if (Objects.isNull(user) || user.getUserName() == null || user.getPassword() == null) {
            throw new SystemException(AppHttpCodeEnum.ARGS_ERROR);
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user.getUserName(), user.getPassword());
        //AuthenticationManager认证，认证不通过，为null
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //认证成功！
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String token = JwtUtil.createJWT(userId);
        //将用户信息存入redis
        redisCache.setCacheObject(SystemConstants.ADMIN_LOGIN_KEY + userId, loginUser);
        //把token封装 返回
        HashMap<String, String> map = new HashMap<>(1);
        map.put("token", token);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        return null;
    }

    @Override
    public AdminUserInfoVo getInfo(Long userId) {
        //先根据用户id获取角色信息 user_role表
        UserRole userRole = userRoleService.getOne(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, userId));
        //再根据角色id获取菜单信息
        //
        AdminUserInfoVo result = new AdminUserInfoVo();
        User userVo = SecurityUtils.getLoginUser().getUser();
        result.setUser(BeanCopyUtils.copyBean(userVo, UserInfoVo.class));
        //result.setPermissions()
        return result;
    }
}
