package cn.icatw.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author icatw
 * @date 2022/10/27
 * @email 762188827@qq.com
 * @apiNote
 */
@SpringBootTest(classes = MenuMapperTest.class)
class MenuMapperTest {
    @Autowired
    MenuMapper menuMapper;

    @Test
    void selectPermsByUserId() {
    }

    @Test
    void testSelectPermsByUserId() {
        List<String> perms = menuMapper.selectPermsByUserId(2L);
        for (String perm : perms) {

            System.out.println(perm);
        }
    }
}
