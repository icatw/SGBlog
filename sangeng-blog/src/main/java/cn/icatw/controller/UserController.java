package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.User;
import cn.icatw.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public ResponseResult updateUserInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }
}
