package cn.icatw.service.impl;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.dto.AddArticleDto;
import cn.icatw.domain.dto.ArticleDto;
import cn.icatw.domain.entity.Article;
import cn.icatw.domain.entity.ArticleTag;
import cn.icatw.domain.entity.Category;
import cn.icatw.domain.vo.*;
import cn.icatw.enums.AppHttpCodeEnum;
import cn.icatw.mapper.ArticleMapper;
import cn.icatw.service.ArticleService;
import cn.icatw.service.ArticleTagService;
import cn.icatw.service.CategoryService;
import cn.icatw.utils.BeanCopyUtils;
import cn.icatw.utils.RedisCache;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Resource
    RedisCache redisCache;
    @Resource
    ArticleTagService articleTagService;

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
        //根据id查询文章
        Article article = getById(id);
        //设置分类名 TODO 非空判断
        //查询文章详情时从Redis中获取
        Category category = categoryService.getById(article.getCategoryId());
        if (category != null) {
            article.setCategoryName(category.getName());
        }
        //文章浏览量从Redis中获取
        Map<String, Integer> map = redisCache.getCacheMap(SystemConstants.VIEW_COUNT_KEY);
        if (map.containsKey(id.toString())) {
            Integer viewCount = map.get(id.toString());
            article.setViewCount(Long.valueOf(viewCount));
        }
        return ResponseResult.okResult(BeanCopyUtils.copyBean(article, ArticleDetailVo.class));
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应 id的浏览量
        redisCache.incrementCacheMapValue(SystemConstants.VIEW_COUNT_KEY, id.toString(), 1);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        List<Long> tags = articleDto.getTags();
        //copy基本的article属性
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        //添加文章和分类表信息 article_tag
        //保存文章信息
        save(article);
        List<ArticleTag> articleTagList = tags.stream().map(tag -> {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setTagId(tag);
            articleTag.setArticleId(article.getId());
            return articleTag;
        }).collect(Collectors.toList());
        articleTagService.saveBatch(articleTagList);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult pageList(Integer pageNum, Integer pageSize, ArticleDto articleDto) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        String summary = articleDto.getSummary();
        String title = articleDto.getTitle();
        if (!StringUtils.isEmpty(summary)) {
            lambdaQueryWrapper.like(Article::getSummary, summary);
        }
        if (!StringUtils.isEmpty(title)) {
            lambdaQueryWrapper.like(Article::getTitle, title);
        }
        page(page, lambdaQueryWrapper);
        List<ArticleAdminVo> voList = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleAdminVo.class);
        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(voList);
        return ResponseResult.okResult(pageVo);
    }
}
