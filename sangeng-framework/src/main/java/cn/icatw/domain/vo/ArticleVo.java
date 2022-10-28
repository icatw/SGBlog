package cn.icatw.domain.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author icatw
 * @date 2022/10/28
 * @email 762188827@qq.com
 * @apiNote
 */
@Data
public class ArticleVo {
    private Long categoryId;
    private String content;
    private Long createBy;
    private Date createTime;
    private Integer delFlag;
    private Long id;
    private String isComment;
    private String isTop;
    private String status;
    private String summary;
    private List<Integer> tags;
    private String thumbnail;
    private String title;
    private Long updateBy;
    private Date updateTime;
    private Long viewCount;
}
