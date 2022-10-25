package cn.icatw.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 热门文章签证官
 *
 * @author icatw
 * @date 2022/10/25
 * @email 762188827@qq.com
 * @apiNote
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {
    private Long id;
    //标题
    private String title;

    //访问量
    private Long viewCount;
}

