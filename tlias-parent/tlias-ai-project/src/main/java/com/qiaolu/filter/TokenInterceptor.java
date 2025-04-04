package com.qiaolu.filter;

import com.qiaolu.utils.CurrentHolder;
import com.qiaolu.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    //目标资源方法执行前执行。 返回true：放行    返回false：不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("preHandle 目标资源方法执行前执行");
        // 1. 获取请求url
        String url = request.getRequestURI();

        // 2. 判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            log.info("登录请求, 直接放行~");
            return true;
        }

        // 3. 不是的话,获取token,为null,则拦截
        String token = request.getHeader("Token");
        log.info("获取Token={}", token);

        if (token == null) {
            log.info("token is null!");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED); // 设置状态码
            return false;
        }

        Claims claims = JwtUtil.parseToken(token);

        if (claims == null) {
            log.info("jwt令牌解析错误~");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }
        Integer id = (Integer) claims.get("id");
        CurrentHolder.setCurrentId(id);
        log.info("获取用户id={}", id);


        log.info("令牌合法,放行!");
        return true;
    }

    //目标资源方法执行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("目标资源方法执行后执行~ postHandle");
    }

    //视图渲染完毕后执行，最后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CurrentHolder.remove();
    }
}
