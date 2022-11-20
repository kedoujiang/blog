package com.jink.jinblog.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @project jin-blog
 * @description
 * @author JINK
 * @date 2022/11/19 20:10:24
 * @version 1.0
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI OpenAPI() {
        return new OpenAPI()
                .components(new Components()
//                        .addSecuritySchemes("BasicAuth", new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP).scheme("basic"))
                                .addSecuritySchemes("BearerAuth", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))
                )
                .addSecurityItem(new SecurityRequirement().addList("BasicAuth").addList("BearerAuth"))
                .info(new Info()
                        .title("SpringDoc API Test")
                        .description("SpringDoc Simple Application Test")
                        .version("0.0.1")
                        .license(license()));
    }


    @Bean
    GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all-api")
                .displayName("All APIs")
                .pathsToMatch("/user/**", "/apis/**")
                .build();
    }


    private License license() {
        return new License()
                .name("MIT")
                .url("https://opensource.org/licenses/MIT");

    }
}
