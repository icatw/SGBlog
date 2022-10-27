package cn.icatw.schedu;

/**
 * @author icatw
 * @date 2022/10/27
 * @email 762188827@qq.com
 * @apiNote
 */
//@Component
public class TestJob {
    //@Scheduled(cron = "0/5 * * * * ?")
    public void testJob() {
        System.out.println("定时任务执行了");
    }
}
