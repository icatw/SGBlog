package cn.icatw.mapper;

import cn.icatw.domain.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 角色和菜单关联表(RoleMenu)表数据库访问层
 *
 * @author icatw
 * @since 2022-10-27 21:23:25
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 被角色菜单ids id
     *
     * @param id id
     * @return {@link List}<{@link Long}>
     */
    List<Long> getMenuIdsByRoleId(@Param("id") Long id);
}

