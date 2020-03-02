package com.hdsx.appservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
//@WebFilter(urlPatterns = { "/api/v/*" }, filterName = "jwtAuthenticationTokenFilter")
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisClient;

    @Value("${spring.redis.namespace}")
    private String scope;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpRequest, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 获取请求的地址
//        String requestURI = httpRequest.getRequestURI();
//
//        // 要是是登录就不管
//        if (requestURI.contains("user") || requestURI.contains("192")) {
//            chain.doFilter(httpRequest, response);
//        } else {
//            try {
//                String token = httpRequest.getHeader("token");
//                String userName = httpRequest.getHeader("userName");
//                if (userName == null) {
//                    userName = "" ;
//                }
//
//                Object tokenServer = redisClient.get(userName);
//                if (token == null  || tokenServer == null ) {
//                    logout(response);
//                } else {
//                    String ts = (String) tokenServer;
//                    if (token.equals(ts)) {
//                        chain.doFilter(httpRequest, response);
//                    }else {
//                        logout(response);
//                    }
//                }
//            } catch (Exception e) {
//                serverError(response);
//                e.printStackTrace();
//            }
//
//
//        }
        chain.doFilter(httpRequest, response);
    }

    private void serverError(HttpServletResponse response) {

    }

}
