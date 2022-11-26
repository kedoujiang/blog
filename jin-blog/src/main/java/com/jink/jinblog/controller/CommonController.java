package com.jink.jinblog.controller;

import com.jink.jinblog.security.PBKDF2PasswordEncoder;
import com.jink.jinblog.security.model.AuthRequest;
import com.jink.jinblog.security.model.AuthResponse;
import com.jink.jinblog.service.UserAuthService;
import com.jink.jinblog.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/20 22:18:11
 */
@RestController
@ResponseBody
public class CommonController {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private PBKDF2PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/login")
    @Operation(summary = "登录接口")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar, ServerHttpRequest request) {
        return userAuthService.findByUsername(ar.getUsername(), request)
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(
                        AuthResponse.
                                builder()
                                .token(jwtUtil.generateToken(userDetails))
                                .build()))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}
