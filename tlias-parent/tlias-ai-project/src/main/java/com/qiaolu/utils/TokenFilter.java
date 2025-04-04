package com.qiaolu.utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*") // 代表过滤一切访问
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init................");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 1. 获取请求url
        String url = httpServletRequest.getRequestURI();

        // 2. 判断请求url中是否包含login，如果包含，说明是登录操作，放行
        if (url.contains("login")) {
            log.info("登录请求, 直接放行~");
            filterChain.doFilter(servletRequest, servletResponse);
        }

        // 3. 不是的话,获取token,为null,则拦截
        String token = httpServletRequest.getHeader("Token");
        if (token == null) {
            log.info("token is null!");
            httpServletResponse.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }

        if (JwtUtil.parseToken(token) == null) {
            log.info("jwt令牌解析错误~");
            httpServletResponse.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }

        log.info("令牌合法,放行!");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("destroy................");
    }
}
