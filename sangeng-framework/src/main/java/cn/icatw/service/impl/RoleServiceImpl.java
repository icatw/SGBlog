package cn.icatw.service.impl;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.dto.RoleDto;
import cn.icatw.domain.entity.Role;
import cn.icatw.domain.vo.PageVo;
import cn.icatw.domain.vo.RoleVo;
import cn.icatw.mapper.RoleMapper;
import cn.icatw.service.RoleService;
import cn.icatw.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author icatw
 * @since 2022-10-27 21:23:25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if (id == 1L) {
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return baseMapper.selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult pageList(Integer pageNum, Integer pageSize, RoleDto roleDto) {
        Page<Role> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        String roleName = roleDto.getRoleName();
        String status = roleDto.getStatus();
        if (!StringUtils.isEmpty(roleName)) {
            wrapper.like(Role::getRoleName, roleName);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq(Role::getStatus, status);
        }
        wrapper.orderByDesc(Role::getRoleSort);
        this.page(page, wrapper);
        List<RoleVo> roleVos = BeanCopyUtils.copyBeanList(page.getRecords(), RoleVo.class);
        PageVo pageVo = new PageVo();
        pageVo.setRows(roleVos);
        pageVo.setTotal(page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}

