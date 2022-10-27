package cn.icatw.schedu;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.entity.Article;
import cn.icatw.mapper.ArticleMapper;
import cn.icatw.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 视图数预算内
 *
 * @author icatw
 * @date 2022/10/27
 * @email 762188827@qq.com
 * @apiNote
 */
@Slf4j
@Component
public class ViewCountJob {
    @Resource
    ArticleMapper articleMapper;
    @Resource
    RedisCache redisCache;

    @Scheduled(cron = "0 0/10 * * * ? ")
    public void updateViewCount() {
        //更新浏览量
        //    先从Redis中获取浏览量map  id和view_count
        Map<String, Integer> map = redisCache.getCacheMap(SystemConstants.VIEW_COUNT_KEY);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Article article = new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue());
            articleMapper.updateById(article);
        }
        log.info("======定时任务======");
        log.info("同步Redis文章浏览量成功");
    }
}
