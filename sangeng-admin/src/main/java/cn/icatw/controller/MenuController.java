package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.Menu;
import cn.icatw.domain.vo.MenuTreeVo;
import cn.icatw.domain.vo.MenuVo;
import cn.icatw.service.MenuService;
import cn.icatw.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author icatw
 * @date 2022/10/28
 * @email 762188827@qq.com
 * @apiNote
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping("/list")
    public ResponseResult list(String status, String menuName) {
        LambdaQueryWrapper<Menu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(status)) {
            lambdaQueryWrapper.eq(Menu::getStatus, status);
        }
        if (!StringUtils.isEmpty(menuName)) {
            lambdaQueryWrapper.like(Menu::getMenuName, menuName);
        }
        lambdaQueryWrapper.orderByDesc(Menu::getParentId, Menu::getOrderNum);
        List<Menu> list = menuService.list(lambdaQueryWrapper);
        return ResponseResult.okResult(list);
    }

    @PostMapping
    public ResponseResult add(@RequestBody Menu menu) {
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    @GetMapping("{id}")
    public ResponseResult getById(@PathVariable Long id) {
        Menu menu = menuService.getById(id);
        MenuVo menuVo = BeanCopyUtils.copyBean(menu, MenuVo.class);
        return ResponseResult.okResult(menuVo);
    }

    @PutMapping
    public ResponseResult update(@RequestBody Menu menu) {
        if (menu.getParentId().equals(menu.getId())) {
            return ResponseResult.errorResult(500, "修改菜单" + menu.getMenuName() + "失败，上级菜单不能选择自己");
        }
        menuService.updateById(menu);
        return ResponseResult.okResult();
    }

    @DeleteMapping("{id}")
    public ResponseResult delete(@PathVariable Long id) {
        //    	能够删除菜单，但是如果要删除的菜单有子菜单则提示：存在子菜单不允许删除 并且删除失败。
        Menu menu = menuService.getById(id);
        //    查询当前菜单是否存在子菜单
        int count = menuService.count(new LambdaQueryWrapper<Menu>()
                .eq(Menu::getParentId, menu.getId()));
        if (count > 0) {
            return ResponseResult.errorResult(500, "存在子菜单不允许删除");
        }
        menuService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/treeselect")
    public ResponseResult treeselect() {
        List<MenuTreeVo> result = menuService.treeselect();
        return ResponseResult.okResult(result);
    }
}
