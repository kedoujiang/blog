package com.jink.jinblog.security;

import com.alibaba.fastjson2.JSONObject;
import com.jink.jinblog.constant.RedisPrefixConst;
import com.jink.jinblog.result.Result;
import com.jink.jinblog.result.ResponseEnum;
import com.jink.jinblog.service.RedisService;
import com.jink.jinblog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.Charset;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 登出处理
 * @date 2022/11/26 22:41:17
 */
@Component
public class ServerLogoutSuccessHandlerImpl implements ServerLogoutSuccessHandler{

    @Autowired
    private RedisService redisService;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        return Mono.defer(() -> Mono.just(exchange.getExchange().getResponse()))
                .flatMap(response -> {
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    String header = authentication.getCredentials().toString();
                    DataBufferFactory dataBufferFactory = response.bufferFactory();
                    String result = JSONObject.toJSONString(Result.of(ResponseEnum.PERMISSION_DENIED));
                    DataBuffer buffer = dataBufferFactory.wrap(result.getBytes(
                            Charset.defaultCharset()));
                    String authToken = header.substring(7);
                    String username = jwtUtil.getUsernameFromToken(authToken);
                    redisService.del(RedisPrefixConst.USER_INFO + username);
                    return response.writeWith(Mono.just(buffer));
                });
    }

}
