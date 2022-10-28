package cn.icatw.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章标签关联表(ArticleTag)实体类
 *
 * @author icatw
 * @since 2022-10-28 11:28:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_article_tag")
@ApiModel("ArticleTag")
public class ArticleTag implements Serializable {
    private static final long serialVersionUID = 868002770921316551L;
    /**
     * 文章id
     */
    //@TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;
    /**
     * 标签id
     */
    //@TableId(value = "tag_id", type = IdType.AUTO)
    private Long tagId;
}

