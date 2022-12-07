package com.jink.jinblog.security;

import com.alibaba.fastjson2.JSONObject;
import com.jink.jinblog.mapper.RoleMapper;
import com.jink.jinblog.result.Result;
import com.jink.jinblog.result.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 鉴权逻辑处理器
 * @date 2022/11/26 22:48:30
 */
@Component
public class ReactiveAuthorizationManagerImpl implements ReactiveAuthorizationManager<AuthorizationContext>{

    @Autowired
    private RoleMapper roleMapper;



    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {

        List<String> roles = roleMapper.listRolesLabel();

        //认证通过且角色匹配的用户可访问当前路径
        return authentication
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authority ->{
                    //检测权限是否匹配
                    if (roles.contains(String.valueOf(authority))) {
                        return true;
                    }
                    return false;
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));

    }

    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
        return check(authentication, object)
                .filter(AuthorizationDecision::isGranted)
                .switchIfEmpty(Mono.defer(() -> {
                    String body = JSONObject.toJSONString(Result.fail(ResponseEnum.PERMISSION_DENIED));
                    return Mono.error(new AccessDeniedException(body));
                }))
                .flatMap(d -> Mono.empty());

    }
}
