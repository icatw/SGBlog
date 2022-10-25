package cn.icatw.service.impl;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.Link;
import cn.icatw.domain.vo.LinkVo;
import cn.icatw.mapper.LinkMapper;
import cn.icatw.service.LinkService;
import cn.icatw.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author icatw
 * @since 2022-10-25 17:59:43
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    /**
     * 得到所有通过审核的友联
     * status 为0
     *
     * @return {@link ResponseResult}
     */
    @Override
    public ResponseResult getAllLink() {
        List<Link> list = list(new LambdaQueryWrapper<Link>()
                .eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL));
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(list, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}

