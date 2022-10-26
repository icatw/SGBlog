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

    /**
     * 评论列表
     *
     * @param articleId 文章id
     * @param pageNum   页面num
     * @param pageSize  页面大小
     * @return {@link ResponseResult}
     */
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(articleId, pageNum, pageSize);
    }

    /**
     * 链接评论列表
     *
     * @param articleId 文章id
     * @param pageNum   页面num
     * @param pageSize  页面大小
     * @return {@link ResponseResult}
     */
    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.linkCommentList(articleId, pageNum, pageSize);
    }

    /**
     * 添加评论
     *
     * @param comment 评论
     * @return {@link ResponseResult}
     */
    @PostMapping
    public ResponseResult pushComment(@RequestBody Comment comment) {
        return commentService.pushComment(comment);
    }
}
