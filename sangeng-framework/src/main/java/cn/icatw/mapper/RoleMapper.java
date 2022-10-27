package cn.icatw.mapper;

import cn.icatw.domain.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author icatw
 * @since 2022-10-27 21:23:24
 */
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 根据用户id查询角色信息key
     *
     * @param id id
     * @return {@link List}<{@link String}>
     */
    List<String> selectRoleKeyByUserId(@Param("id") Long id);
}

