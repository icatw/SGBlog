package cn.icatw.aspect;

import cn.icatw.annotation.SysLog;
import cn.icatw.domain.entity.Log;
import cn.icatw.service.LogService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author icatw
 * @date 2022/10/27
 * @email 762188827@qq.com
 * @apiNote
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    private final Log myLog = new Log();

    @Autowired
    LogService logService;


    @Pointcut("@annotation(cn.icatw.annotation.SysLog)")
    public void pt() {

    }

    @Around("pt()")
    public Object printLog(ProceedingJoinPoint pjp) throws Throwable {
        Object ret;
        try {
            handleBefore(pjp);
            ret = pjp.proceed();
            handleAfter(ret);
        } finally {
            //结束后换行
            log.info("======End======" + System.lineSeparator());
        }
        return ret;
    }

    private void handleAfter(Object ret) {
        // 打印出参
        log.info("Response       : {}", JSON.toJSONString(ret));
        myLog.setResponse(JSON.toJSONString(ret));
        logService.save(myLog);
    }

    private void handleBefore(ProceedingJoinPoint pjp) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取被增强方法上的注解对象
        SysLog sysLog = getSystemLog(pjp);
        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}", request.getRequestURL());
        myLog.setUrl(String.valueOf(request.getRequestURL()));
        // 打印描述信息
        log.info("BusinessName   : {}", sysLog.businessName());
        myLog.setBusinessName(sysLog.businessName());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        myLog.setHttpMethod(request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}",
                pjp.getSignature().getDeclaringTypeName(),
                ((MethodSignature) pjp.getSignature()).getName());
        myLog.setClassMethod(pjp.getSignature().getDeclaringTypeName() + "."
                + ((MethodSignature) pjp.getSignature()).getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteHost());
        myLog.setIp(request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", JSON.toJSONString(pjp.getArgs()));
        myLog.setRequestArgs(JSON.toJSONString(pjp.getArgs()));
        // 结束后换行
        log.info("=======End=======" + System.lineSeparator());
    }

    private SysLog getSystemLog(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        return signature.getMethod().getAnnotation(SysLog.class);
    }
}
