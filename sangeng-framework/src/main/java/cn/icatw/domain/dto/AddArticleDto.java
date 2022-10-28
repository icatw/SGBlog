package cn.icatw.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author icatw
 * @date 2022/10/28
 * @email 762188827@qq.com
 * @apiNote
 */
@Data
public class AddArticleDto {
    private String title;
    private String thumbnail;
    private String isTop;
    private String isComment;
    private String content;
    private List<Long> tags;
    private Long categoryId;
    private String summary;
    private String status;
}
