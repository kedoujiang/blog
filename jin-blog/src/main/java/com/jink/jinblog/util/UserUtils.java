package com.jink.jinblog.util;

import com.jink.jinblog.dto.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/24 21:37:00
 */
@Component
public class UserUtils {

    /**
     * 获取当前登录用户
     *
     * @return 用户登录信息
     */
    public static UserDTO getLoginUser() {
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
