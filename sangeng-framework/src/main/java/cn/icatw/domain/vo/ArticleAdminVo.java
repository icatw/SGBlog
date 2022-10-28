package cn.icatw.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author icatw
 * @date 2022/10/28
 * @email 762188827@qq.com
 * @apiNote
 */
@Data
public class ArticleAdminVo {
    private Long categoryId;
    private String content;
    private Date createTime;
    private Long id;
    private String isComment;
    private String isTop;
    private String status;
    private String summary;
    private String thumbnail;
    private String title;
    private Long viewCount;
}
