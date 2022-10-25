package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.service.LinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author icatw
 * @date 2022/10/25
 * @email 762188827@qq.com
 * @apiNote
 */
@RequestMapping("link")
@RestController
public class LinkController {
    @Resource
    LinkService linkService;

    @GetMapping("getAllLink")
    public ResponseResult getAllLink() {
        return linkService.getAllLink();
    }
}
