package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.User;
import cn.icatw.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }
}
