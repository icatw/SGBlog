package cn.icatw.service;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 友链(Link)表服务接口
 *
 * @author icatw
 * @since 2022-10-25 17:59:43
 */
public interface LinkService extends IService<Link> {

    /**
     * 查询所有审核通过的友联
     *
     * @return {@link ResponseResult}
     */
    ResponseResult getAllLink();
}

