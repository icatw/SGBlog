package cn.icatw.runner;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.entity.Article;
import cn.icatw.mapper.ArticleMapper;
import cn.icatw.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 在项目启动时将文章浏览量存入Redis中
 *
 * @author icatw
 * @date 2022/10/27
 * @email 762188827@qq.com
 * @apiNote
 */
@Component
@Slf4j
public class ViewCountRunner implements CommandLineRunner {
    @Resource
    ArticleMapper articleMapper;
    @Resource
    RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        List<Article> articles = articleMapper.selectList(null);
        Map<String, Integer> map = articles.stream().collect(
                //将文章id和浏览量以map结构存入redis中
                Collectors.toMap(article -> article.getId().toString(),
                        article -> article.getViewCount().intValue()));
        redisCache.setCacheMap(SystemConstants.VIEW_COUNT_KEY, map);
        log.info("===缓存初始化成功===");
    }
}
