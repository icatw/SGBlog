package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.dto.AddRoleDto;
import cn.icatw.domain.dto.RoleDto;
import cn.icatw.domain.dto.RoleStatusDto;
import cn.icatw.domain.entity.Role;
import cn.icatw.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
}
