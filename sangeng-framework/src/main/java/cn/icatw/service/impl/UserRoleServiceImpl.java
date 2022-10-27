package cn.icatw.service.impl;

import cn.icatw.domain.entity.UserRole;
import cn.icatw.mapper.UserRoleMapper;
import cn.icatw.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author icatw
 * @since 2022-10-27 21:23:26
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

