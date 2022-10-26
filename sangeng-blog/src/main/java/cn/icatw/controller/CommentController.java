package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.Comment;
import cn.icatw.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author icatw
 * @date 2022/10/26
 * @email 762188827@qq.com
 * @apiNote
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(articleId, pageNum, pageSize);
    }

    @PostMapping
    public ResponseResult pushComment(@RequestBody Comment comment) {
        return commentService.pushComment(comment);
    }
}
