package com.jink.jinblog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/19 15:57:52
 */
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebFluxSecurityConfigurer {


    @Autowired
    private ReactiveAuthenticationManagerImpl authenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @Autowired
    private ServerAuthenticationFailureHandlerImpl authenticationFailureHandler;

    @Autowired
    private ServerAuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Autowired
    private ServerAuthenticationEntryPointImpl serverAuthenticationEntryPoint;

    @Autowired
    private ServerAccessDeniedHandlerImpl serverAccessDeniedHandler;

    @Autowired
    private ServerLogoutSuccessHandlerImpl serverLogoutSuccessHandler;

    @Autowired
    private ReactiveAuthorizationManagerImpl reactiveAuthorizationManager;


    @Bean
    public SecurityWebFilterChain  securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .securityContextRepository(securityContextRepository)
                .authorizeExchange(exchange -> {

                    exchange.pathMatchers("/login","/swagger-ui.html",
                                    "/webjars/**",
                                    "/swagger-resources/**",
                                    "/v3/**").permitAll()
                            // 拦截认证
                            .pathMatchers(HttpMethod.OPTIONS).permitAll()
                            .anyExchange().access(reactiveAuthorizationManager);
                })
                .exceptionHandling()
                .accessDeniedHandler(serverAccessDeniedHandler)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(serverAuthenticationEntryPoint)
                .and()
                .addFilterAt(authenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                .logout().logoutSuccessHandler(serverLogoutSuccessHandler).and().build();

    }

    private AuthenticationWebFilter authenticationWebFilter() {
        AuthenticationWebFilter filter = new AuthenticationWebFilter(authenticationManager);

        filter.setSecurityContextRepository(securityContextRepository);
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setRequiresAuthenticationMatcher(
                ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login")
        );

        return filter;
    }



}
