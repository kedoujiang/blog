package com.jink.jinblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jink.jinblog.entity.UserAuth;
import com.jink.jinblog.mapper.UserAuthMapper;
import com.jink.jinblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/23 23:39:14
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserAuthMapper userAuthMapper;


}
