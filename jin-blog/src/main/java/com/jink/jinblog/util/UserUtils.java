package com.jink.jinblog.util;

import com.jink.jinblog.dto.UserDetailsDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/24 21:37:00
 */
@Component
@Log4j2
public class UserUtils {

    /**
     * 获取当前登录用户
     *
     * @return 用户登录信息
     */
    public static Mono<UserDetailsDTO> getLoginUser() {

        return ReactiveSecurityContextHolder.getContext()
                .switchIfEmpty(Mono.error(new IllegalStateException("ReactiveSecurityContext is empty")))
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .cast(UserDetailsDTO.class);
    }

    private static Mono<SecurityContext> getSecurityContext() {
        return ReactiveSecurityContextHolder.getContext();
    }
}
