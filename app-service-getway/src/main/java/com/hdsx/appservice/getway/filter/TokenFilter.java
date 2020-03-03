package com.hdsx.appservice.getway.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.core.io.buffer.DataBuffer;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class TokenFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 请求对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String requestUri = request.getPath().pathWithinApplication().value();
        ServerHttpRequest build = mutate.build();
        // 不进行拦截的地址
        if (requestUri.contains("/v2/api-docs")
                || requestUri.contains("/userApi/login")
                || requestUri.contains("/redis")
                || requestUri.contains("/userApi/getVerificationCode")
                || requestUri.contains("/userApi/modifyUserPwdByMessage")) {
            return chain.filter(exchange.mutate().request(build).build());
        }

        return chain.filter(exchange.mutate().request(build).build());

        /**
         * 自己判断一下token逻辑  ↓  token逻辑   ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓
         */
//
//        // 响应对象
//        ServerHttpResponse response = exchange.getResponse();
//
//        // 从请求中获取 token 参数
//        String token = exchange.getRequest().getQueryParams().getFirst("token");
//        // 如果为空，那么将返回 401
//        if (token == null || token.isEmpty()) {
//
//            // 响应消息内容对象
//            JSONObject message = new JSONObject();
//            // 响应状态
//            message.put("code", -1);
//            // 响应内容
//            message.put("msg", "缺少凭证");
//            // 转换响应消息内容对象为字节
//            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
//            DataBuffer buffer = response.bufferFactory().wrap(bits);
//            // 设置响应对象状态码 401
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            // 设置响应对象内容并且指定编码，否则在浏览器中会中文乱码
//            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
//            // 返回响应对象
//            return response.writeWith(Mono.just(buffer));
//        }
//        // 获取请求地址
//        String beforePath = request.getPath().pathWithinApplication().value();
//        // 获取响应状态码
//        HttpStatus beforeStatusCode = response.getStatusCode();
//        System.out.println("响应码：" + beforeStatusCode + "，请求路径：" + beforePath);
//        // 请求前
//        System.out.println("filter -> before");
//        // 如果不为空，就通过
//        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//            // 获取请求地址
//            String afterPath = request.getPath().pathWithinApplication().value();
//            // 获取响应状态码
//            HttpStatus afterStatusCode = response.getStatusCode();
//            System.out.println("响应码：" + afterStatusCode + "，请求路径：" + afterPath);
//            // 响应后
//            System.out.println("filter -> after");
//        }));
    }

}
