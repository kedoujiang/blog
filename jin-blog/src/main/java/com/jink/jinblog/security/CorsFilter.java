package com.jink.jinblog.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description 跨域配置
 * @date 2022/11/19 16:08:51
 */
@Configuration
@EnableWebFlux
public class CorsFilter implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.
                addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
