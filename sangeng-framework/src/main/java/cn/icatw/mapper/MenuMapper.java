package cn.icatw.mapper;

import cn.icatw.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author icatw
 * @since 2022-10-27 21:23:24
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id获取权限信息
     *
     * @param id id
     * @return {@link List}<{@link String}>
     */
    List<String> selectPermsByUserId(@Param("id") Long id);
}

