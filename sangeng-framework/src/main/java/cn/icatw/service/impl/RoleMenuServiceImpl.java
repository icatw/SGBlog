package cn.icatw.service.impl;

import cn.icatw.domain.entity.RoleMenu;
import cn.icatw.mapper.RoleMenuMapper;
import cn.icatw.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author icatw
 * @since 2022-10-27 21:23:25
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}

