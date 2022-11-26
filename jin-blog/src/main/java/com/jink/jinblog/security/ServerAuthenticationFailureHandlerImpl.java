package com.jink.jinblog.security;

import com.alibaba.fastjson2.JSONObject;
import com.jink.jinblog.result.R;
import com.jink.jinblog.result.ResponseEnum;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 认证失败处理
 * @date 2022/11/26 20:20:37
 */
@Component
public class ServerAuthenticationFailureHandlerImpl implements ServerAuthenticationFailureHandler {
    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange().getResponse()).flatMap(response -> {

            DataBufferFactory dataBufferFactory = response.bufferFactory();
            R<Object> result = R.fail(ResponseEnum.GATEWAY_SYS_ERROR);

            // 账号不存在
            if (exception instanceof UsernameNotFoundException) {
                result = R.fail(ResponseEnum.ACCOUNT_NOT_EXIST);
                // 用户名或密码错误
            } else if (exception instanceof BadCredentialsException) {
                result = R.fail(ResponseEnum.LOGIN_PASSWORD_ERROR);
                // 账号已过期
            } else if (exception instanceof AccountExpiredException) {
                result = R.fail(ResponseEnum.ACCOUNT_EXPIRED);
                // 账号已被锁定
            } else if (exception instanceof LockedException) {
                result = R.fail(ResponseEnum.ACCOUNT_LOCKED);
                // 用户凭证已失效
            } else if (exception instanceof CredentialsExpiredException) {
                result = R.fail(ResponseEnum.ACCOUNT_CREDENTIAL_EXPIRED);
                // 账号已被禁用
            } else if (exception instanceof DisabledException) {
                result = R.fail(ResponseEnum.ACCOUNT_DISABLE);
            } else if (exception instanceof AuthenticationServiceException) {
                result.setMsg(exception.getMessage());
            }

            DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(result).getBytes());
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            return response.writeWith(Mono.just(dataBuffer));
        }));

    }
}
