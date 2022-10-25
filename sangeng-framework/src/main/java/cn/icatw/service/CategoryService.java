package cn.icatw.service;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 分类表(Category)表服务接口
 *
 * @author icatw
 * @since 2022-10-25 15:48:21
 */
public interface CategoryService extends IService<Category> {

    /**
     * 得到类别列表
     *
     * @return {@link ResponseResult}
     */
    ResponseResult getCategoryList();
}

