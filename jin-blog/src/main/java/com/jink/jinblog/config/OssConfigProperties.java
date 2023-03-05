package com.jink.jinblog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author JINK
 * @version 1.0
 * @description oss配置属性
 * @date 2023/3/5 19:03:43
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload.oss")
public class OssConfigProperties {

    /**
     * oss域名
     */
    private String url;

    /**
     * 终点
     */
    private String endpoint;

    /**
     * 访问密钥id
     */
    private String accessKeyId;

    /**
     * 访问密钥密码
     */
    private String accessKeySecret;

    /**
     * bucket名称
     */
    private String bucketName;
}
