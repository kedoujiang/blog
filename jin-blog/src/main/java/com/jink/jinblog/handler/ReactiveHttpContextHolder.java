package com.jink.jinblog.handler;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 编写ReactiveHttpContextHolder类用来模拟RequestContextHolder
 * @date 2022/11/27 00:11:24
 */
public class ReactiveHttpContextHolder {

    /**
     * 获取当前请求对象
     * @return
     */
    public static Mono<ServerHttpRequest> getRequest() {
        return Mono.subscriberContext()
                .map(context -> context.get(Info.CONTEXT_KEY).getRequest());
    }

    /**
     * 获取当前response
     * @return
     */
    public static Mono<ServerHttpResponse> getResponse(){
        return Mono.subscriberContext()
                .map(context -> context.get(Info.CONTEXT_KEY).getResponse());
    }

    public static final class Info{
        public static final Class<ServerWebExchange> CONTEXT_KEY = ServerWebExchange.class;
    }
}
