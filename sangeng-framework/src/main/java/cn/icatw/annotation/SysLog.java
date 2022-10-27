package cn.icatw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author icatw
 * @date 2022/10/26
 * @email 762188827@qq.com
 * @apiNote
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SysLog {
    String businessName();
}
