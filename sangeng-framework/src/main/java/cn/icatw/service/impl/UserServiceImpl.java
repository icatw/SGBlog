package cn.icatw.service.impl;

import cn.icatw.domain.entity.User;
import cn.icatw.mapper.UserMapper;
import cn.icatw.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author icatw
 * @since 2022-10-25 19:59:43
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

