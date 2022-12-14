package cn.icatw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author icatw
 * @date 2022/10/25
 * @email 762188827@qq.com
 * @apiNote
 */
@SpringBootApplication
@MapperScan("cn.icatw.mapper")
@EnableScheduling
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
