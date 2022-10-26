package cn.icatw.service;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 评论表(Comment)表服务接口
 *
 * @author icatw
 * @since 2022-10-26 10:09:25
 */
public interface CommentService extends IService<Comment> {

    /**
     * 评论列表
     *
     * @param articleId 文章id
     * @param pageNum   页面num
     * @param pageSize  页面大小
     * @return {@link ResponseResult}
     */
    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);

    /**
     * 发表评论
     *
     * @param comment 评论
     * @return {@link ResponseResult}
     */
    ResponseResult pushComment(Comment comment);
}

