package cn.icatw.service.impl;

import cn.icatw.domain.entity.Tag;
import cn.icatw.mapper.TagMapper;
import cn.icatw.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author icatw
 * @since 2022-10-27 16:51:04
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

