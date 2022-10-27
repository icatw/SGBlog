package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author icatw
 * @date 2022/10/25
 * @email 762188827@qq.com
 * @apiNote
 */
@RestController
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    /**
     * 热门文章列表
     * 根据浏览量排序 前十个
     *
     * @return {@link ResponseResult}
     */
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        return articleService.hotArticleList();
    }

    /**
     * 文章列表
     * 如果有传id则为分类列表
     * 前端默认传分类id为0（查询全部列表）
     *
     * @param pageNum    页面num
     * @param pageSize   页面大小
     * @param categoryId 类别id
     * @return {@link ResponseResult}
     */
    @GetMapping("/articleList")
    public ResponseResult articleList(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam(required = false) Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }

    /**
     * 根据文章id获取文章详情
     *
     * @param id id
     * @return {@link ResponseResult}
     */
    @GetMapping("{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }

    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }
}
