package cn.icatw.aspect;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author icatw
 * @date 2022/10/27
 * @email 762188827@qq.com
 * @apiNote
 */
@Aspect
@Component
@Slf4j
public class ViewCountAspect {

    @Resource
    RedisCache redisCache;

    //aspect和component注解
    //定义切点
    //定义环绕方式
    @Pointcut("@annotation(cn.icatw.annotation.UpdateViewCount)")
    public void pt() {
    }

    @Around("pt()")
    public Object updateViewCount(ProceedingJoinPoint pjp) throws Throwable {

        Object ret;
        try {
            ret = pjp.proceed();
            //获取路径参数
            Object[] articleId = pjp.getArgs();
            log.info("=======浏览量aop-START=======");
            log.info("文章id:{}", articleId);
            //更新redis中对应 id的浏览量
            redisCache.incrementCacheMapValue(SystemConstants.VIEW_COUNT_KEY, String.valueOf(articleId[0]), 1);
            log.info("=======浏览量自增成功！=======");
        } finally {
            //结束后换行
            log.info("======End======" + System.lineSeparator());
        }
        return ret;
    }
}
