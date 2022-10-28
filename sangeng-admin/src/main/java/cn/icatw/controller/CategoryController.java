package cn.icatw.controller;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.Category;
import cn.icatw.domain.vo.CategoryVo;
import cn.icatw.domain.vo.ExcelCategoryVo;
import cn.icatw.enums.AppHttpCodeEnum;
import cn.icatw.service.CategoryService;
import cn.icatw.utils.BeanCopyUtils;
import cn.icatw.utils.WebUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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

    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            //获取需要导出的数据
            List<Category> categoryVos = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);
            //把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //如果出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }
}
