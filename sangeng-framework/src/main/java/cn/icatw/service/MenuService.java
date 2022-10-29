package cn.icatw.service;

import cn.icatw.domain.entity.Menu;
import cn.icatw.domain.vo.MenuTreeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 菜单权限表(Menu)表服务接口
 *
 * @author icatw
 * @since 2022-10-27 21:23:24
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据用户id查询权限信息
     *
     * @param id id
     * @return {@link List}<{@link String}>
     */
    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<MenuTreeVo> treeselect();
}

