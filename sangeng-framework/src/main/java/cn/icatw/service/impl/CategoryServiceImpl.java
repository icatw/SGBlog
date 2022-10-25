package cn.icatw.service.impl;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.Article;
import cn.icatw.domain.entity.Category;
import cn.icatw.domain.vo.CategoryVo;
import cn.icatw.mapper.CategoryMapper;
import cn.icatw.service.ArticleService;
import cn.icatw.service.CategoryService;
import cn.icatw.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author icatw
 * @since 2022-10-25 15:48:21
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        //1.必须为正式发布文章的分类id
        //2.分类必须为启用 status为0
        List<Article> normArticleCateIds = articleService.list(new LambdaQueryWrapper<Article>()
                .eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL));
        //使用set去重
        Set<Long> cateId = normArticleCateIds.stream().map(Article::getCategoryId).collect(Collectors.toSet());
        //根据查询出来的文章分类id去查询分类表信息
        List<Category> categories = list(new LambdaQueryWrapper<Category>()
                .in(Category::getId, cateId)
                .eq(Category::getStatus, SystemConstants.STATUS_NORMAL));
        //封装vo
        List<CategoryVo> result = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(result);
    }
}

