package com.classting.schoolnewsfeed.global.configuration;

import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("뉴스피드 과제")
                        .description("클래스팅 백엔드 개발자 과제 - 유형석")
                        .version("1.0.0"));
    }
}
