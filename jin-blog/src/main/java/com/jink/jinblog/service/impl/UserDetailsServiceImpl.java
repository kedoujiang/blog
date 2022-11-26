package com.jink.jinblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jink.jinblog.constant.RedisPrefixConst;
import com.jink.jinblog.dto.UserDetailsDTO;
import com.jink.jinblog.entity.UserAuth;
import com.jink.jinblog.entity.UserInfo;
import com.jink.jinblog.exception.BizException;
import com.jink.jinblog.mapper.RoleMapper;
import com.jink.jinblog.mapper.UserAuthMapper;
import com.jink.jinblog.mapper.UserInfoMapper;
import com.jink.jinblog.service.ReactiveHttpContextHolder;
import com.jink.jinblog.service.RedisService;
import com.jink.jinblog.util.IpUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.jink.jinblog.constant.CommonConst.SHANGHAI_ZONE;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/26 17:19:00
 */
@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {


    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RedisService redisService;


    @Override
    public Mono<UserDetails> findByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            throw new BizException("用户名不能为空！");
        }
        // 查询账号是否存在
        UserAuth userAuth = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getId, UserAuth::getUserInfoId, UserAuth::getUsername, UserAuth::getPassword, UserAuth::getLoginType)
                .eq(UserAuth::getUsername, username));
        if (Objects.isNull(userAuth)) {
            throw new BizException("用户名不存在!");
        }
        return Mono.just(convertUserDetail(userAuth));
    }


    /**
     * 封装用户登录信息
     *
     * @param user 用户账号
     * @return 用户登录信息
     */
    public UserDetailsDTO convertUserDetail(UserAuth user) {
        ServerHttpRequest request = ReactiveHttpContextHolder.getRequest().block();


        // 查询账号信息
        UserInfo userInfo = userInfoMapper.selectById(user.getUserInfoId());
        // 查询账号角色
        List<String> roleList = roleMapper.listRolesByUserInfoId(userInfo.getId());
        // 查询账号点赞信息
        Set<Object> articleLikeSet = redisService.sMembers(RedisPrefixConst.ARTICLE_USER_LIKE + userInfo.getId());
        Set<Object> commentLikeSet = redisService.sMembers(RedisPrefixConst.COMMENT_USER_LIKE + userInfo.getId());
        Set<Object> talkLikeSet = redisService.sMembers(RedisPrefixConst.TALK_USER_LIKE + userInfo.getId());
        // 获取设备信息
        String ipAddress = IpUtils.getIpAddress(request);
        String ipSource = IpUtils.getIpSource(ipAddress);
        UserAgent userAgent = IpUtils.getUserAgent(request);
        // 封装权限集合
        return UserDetailsDTO.builder()
                .id(user.getId())
                .loginType(user.getLoginType())
                .userInfoId(userInfo.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(userInfo.getEmail()).roleList(roleList)
                .nickname(userInfo.getNickname())
                .avatar(userInfo.getAvatar())
                .intro(userInfo.getIntro())
                .webSite(userInfo.getWebSite())
                .articleLikeSet(articleLikeSet)
                .commentLikeSet(commentLikeSet)
                .talkLikeSet(talkLikeSet)
                .ipAddress(ipAddress)
                .ipSource(ipSource)
                .isDisable(userInfo.getIsDisable())
                .browser(userAgent.getBrowser().getName())
                .os(userAgent.getOperatingSystem().getName())
                .lastLoginTime(LocalDateTime.now(ZoneId.of(SHANGHAI_ZONE))).build();
    }

}
