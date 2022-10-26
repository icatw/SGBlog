package cn.icatw.filter;

import cn.icatw.Constants.SystemConstants;
import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.entity.LoginUser;
import cn.icatw.enums.AppHttpCodeEnum;
import cn.icatw.utils.JwtUtil;
import cn.icatw.utils.RedisCache;
import cn.icatw.utils.WebUtils;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author icatw
 * @date 2022/10/26
 * @email 762188827@qq.com
 * @apiNote
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = request.getHeader("token");
        //如果获取不到请求头
        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        //    解析获取userId
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            //    token超时 token非法
            //    响应告诉前端需要重新登陆
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            String jsonResult = JSON.toJSONString(result);
            WebUtils.renderString(response, jsonResult);
            return;
        }
        String userId = claims.getSubject();
        //    从redis中获取用户信息
        LoginUser redisUser = redisCache.getCacheObject(SystemConstants.LOGIN_KEY + userId);
        if (Objects.isNull(redisUser)) {
            //    说明登陆过期 提示重新登陆
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_EXPIRES);
            String jsonResult = JSON.toJSONString(result);
            WebUtils.renderString(response, jsonResult);
            return;
        }
        //    存入securityContextHolder
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(redisUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
