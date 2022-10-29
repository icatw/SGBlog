package cn.icatw.mapper;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author icatw
 * @date 2022/10/27
 * @email 762188827@qq.com
 * @apiNote
 */
//@SpringBootTest(classes = MenuMapperTest.class)
class MenuMapperTest {
    @Autowired
    MenuMapper menuMapper;

    @Test
    void testRANDOMByMap() {
        Random random = new Random();
        List<Integer> initList = new ArrayList<Integer>();
        for (int i = 0; i < 18; i++) {
            int num = random.nextInt(999) + 1;
            initList.add(num);
        }
        initList.sort(((o1, o2) -> o1 - o2));
        System.out.println("初始数据：" + JSON.toJSONString(initList));

        Map<Integer, List<Integer>> map = new TreeMap<>();
        initList.forEach(i -> {
            if(!map.containsKey(i / 10)){
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(i / 10, list);
            } else {
                List<Integer> list = map.get(i / 10);
                list.add(i);
                map.put(i / 10, list);
            }
        });
        System.out.println(JSON.toJSON(map));
        System.out.println(JSON.toJSON(map.values()));
    }

    @Test
    void testRANDOM() {
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            int r = random.nextInt(999) + 1;
            list.add(r);
        }
        list.sort((o1, o2) -> o1 - o2);
        for (int i = 0; i < list.size(); i++) {
            int cs1;
            //获取除数，如果除数相等则表示为同一个数组
            ArrayList<Integer> tempList = new ArrayList<>();
            tempList.add(list.get(i));
            cs1 = list.get(i) / 10;
            for (int j = i + 1; j < list.size(); j++) {
                int cs2 = list.get(j) / 10;
                if (cs1 == cs2) {
                    tempList.add(list.get(j));
                }
            }
            result.add(tempList);
            for (List<Integer> integers : result) {

            }
        }
        //for (Integer integer : list) {
        //    if (integer/10)
        //    //如果除10相等则表示为同十数组
        //    System.out.println(integer);
        //    System.out.println("模除：" + integer / 10);
        //}
        //System.out.println("ss");
    }

    @Test
    void testSelectPermsByUserId() {
        List<String> perms = menuMapper.selectPermsByUserId(2L);
        for (String perm : perms) {

            System.out.println(perm);
        }
    }
}
