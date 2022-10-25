package cn.icatw.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author icatw
 * @date 2022/10/25
 * @email 762188827@qq.com
 * @apiNote
 */
@Data
public class ArticleDetailVo {
    private Long categoryId;
    private String categoryName;
    private String content;
    private Date createTime;
    private Long id;
    private String isComment;
    private String title;
    private Long viewCount;
}
