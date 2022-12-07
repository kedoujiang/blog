package com.jink.jinblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jink.jinblog.dto.UserDetailsDTO;
import com.jink.jinblog.entity.UserAuth;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/21 23:11:02
 */

public interface UserAuthService extends IService<UserAuth> {


    /**
     * 登录处理
     * @param username
     * @param request
     * @return
     */
    UserDetailsDTO loginByUserName(String username, ServerHttpRequest request);
}
