package com.jink.jinblog.security;

import com.alibaba.fastjson2.JSONObject;
import com.jink.jinblog.dto.UserDetailsDTO;
import com.jink.jinblog.dto.UserInfoDTO;
import com.jink.jinblog.security.model.AuthRequest;
import com.jink.jinblog.service.UserAuthService;
import com.jink.jinblog.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerFormLoginAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 修改为Post，application/json 请求方式
 * @date 2022/11/27 14:23:34
 */
@Component
public class ServerFormLoginAuthenticationConverterImpl extends ServerFormLoginAuthenticationConverter {


    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private Pbkdf2PasswordEncoder passwordEncoder;

    public Mono<Authentication> convert(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        return exchange
                .getRequest()
                .getBody()
                .next()
                .publishOn(Schedulers.boundedElastic())
                .flatMap(body -> {
                    // 读取请求体
                    try {
                        AuthRequest authRequest = JSONObject.parseObject(body.toString(StandardCharsets.UTF_8),AuthRequest.class);
                        UserDetailsDTO userDetailsDTO = userAuthService.loginByUserName(authRequest.getUsername(), request);
                        if(passwordEncoder.encode(authRequest.getPassword()).equals(userDetailsDTO.getPassword())){
                            String token = jwtUtil.generateToken(userDetailsDTO);
                            UserInfoDTO userInfoDTO = new UserInfoDTO();
                            BeanUtils.copyProperties(userDetailsDTO,userInfoDTO);
                            Authentication auth = new UsernamePasswordAuthenticationToken(userInfoDTO, token);
                            return Mono.just(auth);
                        }
                        return Mono.error(new AuthenticationServiceException("Password error please try again"));
                    } catch (Exception e) {
                        return Mono.error(new AuthenticationServiceException(e.getMessage()));
                    }
                });

    }

}
