package cn.icatw.service.impl;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.Article;
import cn.icatw.domain.entity.Category;
import cn.icatw.domain.vo.ArticleDetailVo;
import cn.icatw.domain.vo.ArticleListVo;
import cn.icatw.domain.vo.HotArticleVo;
import cn.icatw.domain.vo.PageVo;
import cn.icatw.enums.AppHttpCodeEnum;
import cn.icatw.mapper.ArticleMapper;
import cn.icatw.service.ArticleService;
import cn.icatw.service.CategoryService;
import cn.icatw.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author icatw
 * @date 2022/10/25
 * @email 762188827@qq.com
 * @apiNote
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Resource
    CategoryService categoryService;

    /**
     * 热门文章列表
     *
     * @return {@link ResponseResult}
     */
    @Override
    public ResponseResult hotArticleList() {
        //分页前十个
        //浏览量排序 view_count
        //正式发布文章 status为0
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getViewCount);
        Page<Article> page = baseMapper.selectPage(new Page<Article>(1, 10), wrapper);
        //优化vo返回 stream流式
        //List<HotArticleVo> result = page.getRecords().stream().map(article -> {
        //    HotArticleVo vo = new HotArticleVo();
        //    BeanUtils.copyProperties(article, vo);
        //    return vo;
        //}).collect(Collectors.toList());
        //copy VoList
        List<HotArticleVo> result = BeanCopyUtils.copyBeanList(page.getRecords(), HotArticleVo.class);
        return ResponseResult.okResult(result);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //1.查询正式发表的文章 status为1
        //2.按照istop置顶排序
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getIsTop);
        //如果有分类id，拼接上分类id，默认会传categoryId为0 查询全部
        if (Objects.nonNull(categoryId) && categoryId > 0) {
            wrapper.eq(Article::getCategoryId, categoryId);
        }
        Page<Article> page = page(new Page<>(pageNum, pageSize), wrapper);
        //转文章列表vo，设置分类名
        List<Article> articles = page.getRecords();
        articles.forEach(article -> {
            //流式获取文章分类名
            article.setCategoryName(categoryService
                    .getById(article.getCategoryId()).getName());
        });
        List<ArticleListVo> listVos = BeanCopyUtils.copyBeanList(articles, ArticleListVo.class);
        //封装pageVo返回
        PageVo pageVo = new PageVo();
        pageVo.setRows(listVos);
        pageVo.setTotal(page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        if (Objects.isNull(id)) {
            //参数有误!
            return ResponseResult.errorResult(AppHttpCodeEnum.Parameter_ERROR);
        }
        Article article = getById(id);
        //设置分类名 TODO 非空判断
        Category category = categoryService.getById(article.getCategoryId());
        if (category != null) {
            article.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(BeanCopyUtils.copyBean(article, ArticleDetailVo.class));
    }
}
