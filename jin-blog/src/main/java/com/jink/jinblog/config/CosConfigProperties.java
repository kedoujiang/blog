package com.jink.jinblog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author JINK
 * @version 1.0
 * @description cos存储接入
 * @date 2023/3/5 19:16:01
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload.cos")
public class CosConfigProperties {

    private String url;

    private String secretId;

    private String secretKey;

    private String region;

    private String bucketName;
}
