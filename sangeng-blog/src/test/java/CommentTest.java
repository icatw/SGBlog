import cn.icatw.BlogApplication;
import cn.icatw.domain.ResponseResult;
import cn.icatw.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author icatw
 * @date 2022/10/26
 * @email 762188827@qq.com
 * @apiNote
 */
@SpringBootTest(classes = BlogApplication.class)
public class CommentTest {
    @Autowired
    CommentService commentService;

    @Test
    public void testComment() {
        ResponseResult list = commentService.commentList(1L, 1, 10);
        System.out.println(list.toString());
    }
}
