package com.example.growertech.config;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Your API Title").version("1.0.0").description("Your API description"))
                .externalDocs(new ExternalDocumentation()
                        .description("Find more info here")
                        .url("https://example.com"));
    }

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
                .group("custom")
                .pathsToMatch("/custom/**")
                .build();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/swagger-ui/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/")
                        .resourceChain(false);
                registry.addResourceHandler("/swagger-ui-resources/**")
                        .addResourceLocations("classpath:/META-INF/resources/springdoc/swagger-ui/")
                        .resourceChain(false);
                registry.addResourceHandler("/v3/api-docs/**")
                        .addResourceLocations("classpath:/META-INF/resources/")
                        .resourceChain(false);
                registry.addResourceHandler("/v3/api-docs.yaml/**")
                        .addResourceLocations("classpath:/META-INF/resources/")
                        .resourceChain(false);
                registry.addResourceHandler("/swagger-ui/oauth2-redirect.html")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/springdoc-openapi-ui/")
                        .resourceChain(false);
            }
        };
    }
}
