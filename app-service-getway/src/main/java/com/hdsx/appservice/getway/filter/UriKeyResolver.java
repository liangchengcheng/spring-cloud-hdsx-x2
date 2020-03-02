package com.hdsx.appservice.getway.filter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关限流过滤器
 */
public class UriKeyResolver implements KeyResolver {

    /*
     * @ClassName UriKeyResolver
     * @Desc TODO   根据请求的 uri 限流
     * @Date 2019/6/29 17:25
     * @Version 1.0
     */
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getURI().getPath());
    }

}
