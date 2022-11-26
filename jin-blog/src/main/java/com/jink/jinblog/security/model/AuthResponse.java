package com.jink.jinblog.security.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/19 17:45:50
 */
@Data
@Builder
public class AuthResponse {
    private String token;
}
