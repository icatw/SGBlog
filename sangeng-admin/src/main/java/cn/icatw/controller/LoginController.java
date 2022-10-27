package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.LoginUser;
import cn.icatw.domain.entity.User;
import cn.icatw.domain.vo.AdminUserInfoVo;
import cn.icatw.domain.vo.UserInfoVo;
import cn.icatw.enums.AppHttpCodeEnum;
import cn.icatw.service.LoginService;
import cn.icatw.service.MenuService;
import cn.icatw.service.RoleService;
import cn.icatw.utils.BeanCopyUtils;
import cn.icatw.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author icatw
 * @date 2022/10/27
 * @email 762188827@qq.com
 * @apiNote
 */
@RestController
public class LoginController {
    @Resource
    LoginService loginService;
    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @GetMapping("/getinfo")
    public ResponseResult<AdminUserInfoVo> getInfo() {
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (Objects.isNull(loginUser)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //封装数据返回

        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }
}
