package cn.icatw.controller;

import cn.icatw.annotation.SysLog;
import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.User;
import cn.icatw.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author icatw
 * @date 2022/10/26
 * @email 762188827@qq.com
 * @apiNote
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo() {
        return userService.getUserInfo();
    }

    @PutMapping("/userInfo")
    @SysLog(businessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody @Valid User user) {
        return userService.register(user);
    }
}
