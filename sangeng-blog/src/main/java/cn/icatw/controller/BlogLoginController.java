package cn.icatw.controller;

import cn.icatw.annotation.SysLog;
import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.User;
import cn.icatw.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author icatw
 * @date 2022/10/25
 * @email 762188827@qq.com
 * @apiNote
 */
@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    @SysLog(businessName = "用户登陆")
    public ResponseResult login(@RequestBody User user) {
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    @SysLog(businessName = "用户注销")
    public ResponseResult logout() {

        return blogLoginService.logout();
    }
}
