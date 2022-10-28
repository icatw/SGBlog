package cn.icatw.controller;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.Category;
import cn.icatw.domain.vo.CategoryVo;
import cn.icatw.service.CategoryService;
import cn.icatw.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author icatw
 * @date 2022/10/28
 * @email 762188827@qq.com
 * @apiNote
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory() {
        List<Category> list = categoryService.list(new LambdaQueryWrapper<Category>()
                .eq(Category::getStatus, SystemConstants.STATUS_NORMAL));
        List<CategoryVo> result = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return ResponseResult.okResult(result);
    }
}
