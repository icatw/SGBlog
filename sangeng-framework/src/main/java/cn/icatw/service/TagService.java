package cn.icatw.service;

import cn.icatw.domain.dto.TagListDto;
import cn.icatw.domain.entity.Tag;
import cn.icatw.domain.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 标签(Tag)表服务接口
 *
 * @author icatw
 * @since 2022-10-27 16:51:04
 */
public interface TagService extends IService<Tag> {

    /**
     * 条件查询分页标签
     *
     * @param pageNum    页面num
     * @param pageSize   页面大小
     * @param tagListDto 标记列表dto
     * @return {@link PageVo}
     */
    PageVo pageList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

}

