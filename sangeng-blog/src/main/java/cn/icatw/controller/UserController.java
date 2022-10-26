package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
