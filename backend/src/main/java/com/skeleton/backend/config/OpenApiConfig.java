package com.skeleton.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI skeletonOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Backend Skeleton API")
                        .description("Spring Boot 骨架 API：健康检查与示例资源")
                        .version("0.1.0"));
    }
}
