package com.jink.jinblog.security;

import com.jink.jinblog.constant.RedisPrefixConst;
import com.jink.jinblog.dto.UserInfoDTO;
import com.jink.jinblog.service.RedisService;
import com.jink.jinblog.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Resource;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 上下文验证器
 * @date 2022/11/19 16:17:51
 */
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    @Autowired
    private ReactiveAuthenticationManagerImpl authenticationManager;

    @Autowired
    private RedisService redisService;

    @Resource
    private JwtUtil jwtUtil;


    public SecurityContextRepository(ReactiveAuthenticationManagerImpl authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {

        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(authHeader -> authHeader.startsWith("Bearer "))
                .publishOn(Schedulers.boundedElastic())
                .flatMap(authHeader -> {
                    String authToken = authHeader.substring(7);
                    String username = jwtUtil.getUsernameFromToken(authToken);
                    UserInfoDTO userInfoDTO = (UserInfoDTO) redisService.get(RedisPrefixConst.USER_INFO + username);
                    Authentication auth = new UsernamePasswordAuthenticationToken(userInfoDTO, authToken);
                    return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
                });
    }
}
