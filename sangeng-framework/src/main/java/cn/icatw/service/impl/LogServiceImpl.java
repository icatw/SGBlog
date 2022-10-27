package cn.icatw.service.impl;

import cn.icatw.domain.entity.Log;
import cn.icatw.mapper.LogMapper;
import cn.icatw.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统日志(Log)表服务实现类
 *
 * @author icatw
 * @since 2022-10-27 10:45:42
 */
@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}

