package cn.icatw.service.impl;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.entity.Menu;
import cn.icatw.domain.vo.MenuTreeVo;
import cn.icatw.mapper.MenuMapper;
import cn.icatw.service.MenuService;
import cn.icatw.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author icatw
 * @since 2022-10-27 21:23:24
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员，返回所有的权限
        if (id == 1L) {
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(wrapper);
            return menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
        }
        //否则返回所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断是否是管理员
        if (SecurityUtils.isAdmin()) {
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        } else {
            //否则  获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        return builderMenuTree(menus, 0L);
    }

    @Override
    public List<MenuTreeVo> treeselect() {
        return getMenuTreeSelect();
    }

    private List<Menu> builderMenuTree(List<Menu> menus, Long parentId) {
        return menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .peek(menu -> menu.setChildren(getChildren(menu, menus))).collect(Collectors.toList());
    }

    /**
     * 获取存入参数的 子Menu集合
     *
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        return menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .peek(m -> m.setChildren(getChildren(m, menus)))
                .collect(Collectors.toList());
    }

    //获取菜单树
    private List<MenuTreeVo> getMenuTreeSelect() {
        List<Menu> menus = baseMapper.selectList(null);
        List<MenuTreeVo> treeVos = menus.stream().map(menu -> new MenuTreeVo(new ArrayList<>(), menu.getId(), menu.getMenuName(), menu.getParentId())).collect(Collectors.toList());
        return buildTreeMenuTree(treeVos, 0L);
    }

    private List<MenuTreeVo> buildTreeMenuTree(List<MenuTreeVo> menus, Long parentId) {
        return menus.stream()
                .filter(menu -> parentId.equals(menu.getParentId()))
                //获取该菜单下的子菜单，并设置到children属性中
                .peek(menu -> menu.setChildren(getChildrenMenusTree(menu, menus)))
                .collect(Collectors.toList());
    }

    private List<MenuTreeVo> getChildrenMenusTree(MenuTreeVo menu, List<MenuTreeVo> menus) {
        return menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                //考虑到三级甚至多级菜单的情况下，
                // 需要继续找到子菜单的子菜单...并设置children属性,
                // 于是这里继续遍历每个菜单，寻找其子菜单并添加，调用自身的找子菜单方法，实现递归
                .peek(m -> m.setChildren(getChildrenMenusTree(m, menus)))
                .collect(Collectors.toList());
    }
}

