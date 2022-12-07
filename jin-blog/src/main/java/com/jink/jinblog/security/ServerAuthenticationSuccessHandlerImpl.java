package com.jink.jinblog.security;

import com.alibaba.fastjson2.JSONObject;
import com.jink.jinblog.constant.RedisPrefixConst;
import com.jink.jinblog.dto.UserInfoDTO;
import com.jink.jinblog.entity.UserAuth;
import com.jink.jinblog.mapper.UserAuthMapper;
import com.jink.jinblog.result.Result;
import com.jink.jinblog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 认证成功处理
 * @date 2022/11/26 21:53:54
 */
@Component
public class ServerAuthenticationSuccessHandlerImpl implements ServerAuthenticationSuccessHandler {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange().getResponse())
                .publishOn(Schedulers.boundedElastic())
                .flatMap(response -> {
                    DataBufferFactory dataBufferFactory = response.bufferFactory();
                    response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(Result.ok(authentication.getCredentials())).getBytes());
                    UserInfoDTO userInfoDTO = (UserInfoDTO) authentication.getPrincipal();
                    redisService.set(RedisPrefixConst.USER_INFO + userInfoDTO.getUsername(), userInfoDTO, 3600);
                    updateUserInfo(userInfoDTO);
                    return response.writeWith(Mono.just(dataBuffer));
                }));
    }


    @Async
    public void updateUserInfo(UserInfoDTO userInfoDTO) {
        UserAuth userAuth = UserAuth.builder()
                .id(userInfoDTO.getId())
                .ipAddress(userInfoDTO.getIpAddress())
                .ipSource(userInfoDTO.getIpSource())
                .lastLoginTime(userInfoDTO.getLastLoginTime())
                .build();
        userAuthMapper.updateById(userAuth);
    }
}
