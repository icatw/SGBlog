package cn.icatw.service;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.dto.AddRoleDto;
import cn.icatw.domain.dto.RoleDto;
import cn.icatw.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author icatw
 * @since 2022-10-27 21:23:25
 */
public interface RoleService extends IService<Role> {

    /**
     * 选择关键角色用户id
     *
     * @param id id
     * @return {@link List}<{@link String}>
     */
    List<String> selectRoleKeyByUserId(Long id);

    ResponseResult pageList(Integer pageNum, Integer pageSize, RoleDto roleDto);

    void add(AddRoleDto roleDto);
}

