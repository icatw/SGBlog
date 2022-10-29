package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.dto.AddRoleDto;
import cn.icatw.domain.dto.RoleDto;
import cn.icatw.domain.dto.RoleStatusDto;
import cn.icatw.domain.entity.Role;
import cn.icatw.domain.entity.RoleMenu;
import cn.icatw.domain.vo.RoleVo;
import cn.icatw.service.RoleMenuService;
import cn.icatw.service.RoleService;
import cn.icatw.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author icatw
 * @date 2022/10/28
 * @email 762188827@qq.com
 * @apiNote
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Resource
    RoleService roleService;
    @Resource
    RoleMenuService roleMenuService;

    @GetMapping("/list")
    public ResponseResult list(Integer pageNum, Integer pageSize, RoleDto roleDto) {
        return roleService.pageList(pageNum, pageSize, roleDto);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleStatusDto roleStatusDto) {
        //    改变状态
        LambdaUpdateWrapper<Role> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Role::getId, roleStatusDto.getRoleId())
                .set(Role::getStatus, roleStatusDto.getStatus());
        roleService.update(lambdaUpdateWrapper);
        return ResponseResult.okResult();
    }

    @PostMapping
    public ResponseResult add(@RequestBody AddRoleDto roleDto) {
        roleService.add(roleDto);
        return ResponseResult.okResult();
    }

    @PutMapping
    public ResponseResult update(@RequestBody AddRoleDto roleDto) {
        Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
        roleService.updateById(role);
        List<Integer> menuIds = roleDto.getMenuIds();
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>()
                .eq(RoleMenu::getRoleId, role.getId()));
        //    先删除再添加
        menuIds.stream().map(id -> new RoleMenu(role.getId(), id.longValue()))
                .forEach(roleMenu -> roleMenuService.save(roleMenu));
        return ResponseResult.okResult();
    }

    @GetMapping("{id}")
    public ResponseResult getById(@PathVariable Long id) {
        Role role = roleService.getById(id);
        RoleVo result = BeanCopyUtils.copyBean(role, RoleVo.class);
        return ResponseResult.okResult(result);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteById(@PathVariable Long id) {
        roleService.removeById(id);
        return ResponseResult.okResult();
    }
}
