package com.jink.jinblog.security;

import com.alibaba.fastjson2.JSONObject;
import com.jink.jinblog.result.Result;
import com.jink.jinblog.result.ResponseEnum;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 鉴权失败处理器
 * @date 2022/11/26 22:21:45
 */
@Component
public class ServerAccessDeniedHandlerImpl implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        return Mono
                .defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> {
                    response.setStatusCode(HttpStatus.FORBIDDEN);
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    DataBufferFactory dataBufferFactory = response.bufferFactory();
                    String result = JSONObject.toJSONString(Result.of(ResponseEnum.PERMISSION_DENIED));
                    DataBuffer buffer = dataBufferFactory.wrap(result.getBytes(
                            StandardCharsets.UTF_8));
                    return response.writeWith(Mono.just(buffer));
                });


    }
}
