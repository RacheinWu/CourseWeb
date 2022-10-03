package com.he.ssm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * @author itaem
 * date:2020-09-21 2020/9/21:17:08
 * http://localhost:8080/swagger-ui/
 */
@EnableOpenApi
@Configuration
public class Swagger3Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                // 给接口分组
                .groupName("API")
                .apiInfo(apiInfo("API文档","API文档的描述","1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.he.ssm.api"))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo(String title,String desc,String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description(desc)
                .version(version)
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        // header、cookie、query
        return Collections.singletonList(new ApiKey("Authorization", "token", "header"));
    }

    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(
                                Collections.singletonList(
                                        new SecurityReference("Authorization", new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")})
                                )
                        )
                        // .operationSelector()
                        .build()
        );
    }
}
