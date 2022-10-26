package cn.icatw.service.impl;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.Comment;
import cn.icatw.domain.entity.User;
import cn.icatw.domain.vo.CommentVo;
import cn.icatw.domain.vo.PageVo;
import cn.icatw.enums.AppHttpCodeEnum;
import cn.icatw.exception.SystemException;
import cn.icatw.mapper.CommentMapper;
import cn.icatw.service.CommentService;
import cn.icatw.service.UserService;
import cn.icatw.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author icatw
 * @since 2022-10-26 10:09:25
 */
@Service
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    UserService userService;

    @Override
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        //先查询文章的根评论 root_id为-1
        return getCommentList(articleId, SystemConstants.ARTICLE_COMMENT, pageNum, pageSize);
    }

    @Override
    public ResponseResult pushComment(Comment comment) {
        //评论内容不能为空
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult linkCommentList(Long articleId, Integer pageNum, Integer pageSize) {
        return getCommentList(articleId, SystemConstants.LINK_COMMENT, pageNum, pageSize);
    }

    private ResponseResult getCommentList(Long articleId, String commentType, Integer pageNum, Integer pageSize) {
        //先查询文章的根评论 root_id为-1
        //TODO 如果type为1，表示查询友联评论，则无需添加文章id条件
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getArticleId, articleId)
                .eq(Comment::getRootId, SystemConstants.COMMENT_ROOT_ID)
                .eq(Comment::getType, commentType)
                .orderByDesc(Comment::getCreateTime);
        Page<Comment> page = new Page<>(pageNum, pageSize);
        Page<Comment> commentPage = page(page, lambdaQueryWrapper);
        //根评论
        List<Comment> commentList = commentPage.getRecords();
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(commentList, CommentVo.class);
        commentVos.forEach(this::getAndSetChildren);
        //封装pageVo返回
        log.info("commentVos==" + commentVos.toString());
        PageVo result = new PageVo();
        result.setRows(commentVos);
        result.setTotal(page.getTotal());
        return ResponseResult.okResult(result);
    }

    private void getAndSetChildren(CommentVo comment) {
        toCommentVo(comment);
        //获取子评论，子评论的root_id为当前id
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getRootId, comment.getId())
                .orderByDesc(Comment::getCreateTime);
        List<Comment> twoComments = list(wrapper);
        List<CommentVo> twoCommentVos = BeanCopyUtils.copyBeanList(twoComments, CommentVo.class);
        twoCommentVos.forEach(this::toCommentVo);
        comment.setChildren(twoCommentVos);
    }

    private void toCommentVo(CommentVo two) {
        Long toUserId2 = two.getToCommentUserId();
        //回复人id获取去回复的人名字，commentUserId为-1代表顶级评论，没有需要去回复的人
        if (two.getToCommentUserId() != -1) {
            User toUser = userService.getById(toUserId2);
            two.setToCommentUserName(toUser.getNickName());
        }
        //评论人名字 对应数据库的create_by的用户id
        User user2 = userService.getById(two.getCreateBy());
        two.setUsername(user2.getNickName());
    }
}

