package cn.icatw.service.impl;

import cn.icatw.domain.dto.TagListDto;
import cn.icatw.domain.entity.Tag;
import cn.icatw.domain.vo.PageVo;
import cn.icatw.domain.vo.TagVo;
import cn.icatw.mapper.TagMapper;
import cn.icatw.service.TagService;
import cn.icatw.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author icatw
 * @since 2022-10-27 16:51:04
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public PageVo pageList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        Page<Tag> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        String name = tagListDto.getName();
        String remark = tagListDto.getRemark();
        if (StringUtils.isNotEmpty(name)) {
            wrapper.like(Tag::getName, name);
        }
        if (StringUtils.isNotEmpty(remark)) {
            wrapper.like(Tag::getRemark, remark);
        }
        page(page, wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(page.getRecords(), TagVo.class);
        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(tagVos);
        return pageVo;
    }
}

