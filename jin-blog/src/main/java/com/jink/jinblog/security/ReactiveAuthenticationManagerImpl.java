package com.jink.jinblog.security;

import com.jink.jinblog.dto.UserInfoDTO;
import com.jink.jinblog.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/19 16:13:12
 */
@Component
public class ReactiveAuthenticationManagerImpl implements ReactiveAuthenticationManager {

    @Resource
    private JwtUtil jwtUtil;

    public ReactiveAuthenticationManagerImpl(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        UserInfoDTO userInfoDTO = (UserInfoDTO) authentication.getPrincipal();
        return Mono.just(jwtUtil.validateToken(authToken))
                .filter(valid -> valid)
                .switchIfEmpty(Mono.empty())
                .map(valid -> {
                    Claims claims = jwtUtil.getAllClaimsFromToken(authToken);
                    List<String> rolesMap = claims.get("role", List.class);
                    return new UsernamePasswordAuthenticationToken(
                            userInfoDTO,
                            authToken,
                            rolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                    );
                });
    }
}
