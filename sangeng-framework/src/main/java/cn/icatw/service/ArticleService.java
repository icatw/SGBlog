package cn.icatw.service;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.dto.AddArticleDto;
import cn.icatw.domain.dto.ArticleDto;
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

    /**
     * 更新文章浏览量
     *
     * @param id 文章id
     * @return {@link ResponseResult}
     */
    ResponseResult updateViewCount(Long id);

    /**
     * 发表文章
     *
     * @param article 文章
     * @return {@link ResponseResult}
     */
    ResponseResult add(AddArticleDto article);

    /**
     * 分页条件查询
     *
     * @param pageNum    页面num
     * @param pageSize   页面大小
     * @param articleDto 文章dto
     * @return {@link ResponseResult}
     */
    ResponseResult pageList(Integer pageNum, Integer pageSize, ArticleDto articleDto);
}
