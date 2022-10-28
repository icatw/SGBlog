package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.dto.AddArticleDto;
import cn.icatw.domain.dto.ArticleDto;
import cn.icatw.domain.entity.Article;
import cn.icatw.domain.entity.ArticleTag;
import cn.icatw.domain.vo.ArticleVo;
import cn.icatw.enums.AppHttpCodeEnum;
import cn.icatw.exception.SystemException;
import cn.icatw.service.ArticleService;
import cn.icatw.service.ArticleTagService;
import cn.icatw.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author icatw
 * @date 2022/10/28
 * @email 762188827@qq.com
 * @apiNote
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    ArticleTagService articleTagService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article) {
        return articleService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult getAllArticle(Integer pageNum, Integer pageSize, ArticleDto articleDto) {
        return articleService.pageList(pageNum, pageSize, articleDto);
    }

    @GetMapping("{id}")
    public ResponseResult getArticleById(@PathVariable Long id) {
        Article article = articleService.getById(id);
        if (Objects.isNull(article)) {
            return ResponseResult.errorResult(500, "文章查询不到！");
        }
        ArticleVo articleVo = BeanCopyUtils.copyBean(article, ArticleVo.class);
        List<ArticleTag> tagList = articleTagService.list(new LambdaQueryWrapper<ArticleTag>()
                .eq(ArticleTag::getArticleId, id)
                .select(ArticleTag::getTagId));
        List<Integer> tagIds = tagList.stream().map(articleTag -> articleTag.getTagId().intValue())
                .collect(Collectors.toList());
        articleVo.setTags(tagIds);
        return ResponseResult.okResult(articleVo);
    }

    @PutMapping
    public ResponseResult update(@RequestBody ArticleVo vo) {
        //修改文章
        //查询
        if (Objects.isNull(vo) || vo.getId() == null) {
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        Article article = BeanCopyUtils.copyBean(vo, Article.class);
        articleService.updateById(article);
        LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ArticleTag::getArticleId, article.getId());
        //先删除，再新增
        articleTagService.remove(queryWrapper);
        vo.getTags().forEach(t -> {
            articleTagService.save(new ArticleTag(article.getId(), t.longValue()));
        });
        return ResponseResult.okResult();
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteById(@PathVariable Long id) {
        articleService.removeById(id);
        articleTagService.remove(new LambdaQueryWrapper<ArticleTag>()
                .eq(ArticleTag::getArticleId, id));
        return ResponseResult.okResult();
    }
}
