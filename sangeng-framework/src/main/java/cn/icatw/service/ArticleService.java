package cn.icatw.service;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author icatw
 * @date 2022/10/25
 * @email 762188827@qq.com
 * @apiNote
 */
public interface ArticleService extends IService<Article> {
    /**
     * 热门文章列表
     *
     * @return {@link ResponseResult}
     */
    ResponseResult hotArticleList();

    /**
     * 文章列表
     *
     * @param pageNum    页码
     * @param pageSize   每页记录数
     * @param categoryId 分类id
     * @return {@link ResponseResult}
     */
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    /**
     * 获取文章详情
     *
     * @param id id
     * @return {@link ResponseResult}
     */
    ResponseResult getArticleDetail(Long id);
}
