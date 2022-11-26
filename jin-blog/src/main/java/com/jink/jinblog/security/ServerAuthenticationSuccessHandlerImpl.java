package com.jink.jinblog.security;

import com.alibaba.fastjson2.JSONObject;
import com.jink.jinblog.dto.UserDetailsDTO;
import com.jink.jinblog.dto.UserInfoDTO;
import com.jink.jinblog.result.R;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 认证成功处理
 * @date 2022/11/26 21:53:54
 */
@Component
public class ServerAuthenticationSuccessHandlerImpl implements ServerAuthenticationSuccessHandler {
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange().getResponse())
                .flatMap(response -> {
                    DataBufferFactory dataBufferFactory = response.bufferFactory();
                    UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getPrincipal();
                    UserInfoDTO userInfoDTO = new UserInfoDTO();
                    BeanUtils.copyProperties(userDetailsDTO, userInfoDTO);
                    response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(R.ok(userInfoDTO)).getBytes());
                    return response.writeWith(Mono.just(dataBuffer));
                }));
    }
}
