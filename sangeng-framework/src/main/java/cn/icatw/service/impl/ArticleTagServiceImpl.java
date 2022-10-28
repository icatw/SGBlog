package cn.icatw.service.impl;

import cn.icatw.domain.entity.ArticleTag;
import cn.icatw.mapper.ArticleTagMapper;
import cn.icatw.service.ArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author icatw
 * @since 2022-10-28 11:28:16
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}

