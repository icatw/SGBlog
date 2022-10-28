package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.dto.AddArticleDto;
import cn.icatw.domain.dto.ArticleDto;
import cn.icatw.domain.entity.Article;
import cn.icatw.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article) {
        return articleService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult getAllArticle(Integer pageNum, Integer pageSize, ArticleDto articleDto) {
        return articleService.pageList(pageNum, pageSize, articleDto);
    }
}
