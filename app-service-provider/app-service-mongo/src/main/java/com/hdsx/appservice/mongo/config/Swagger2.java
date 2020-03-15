package com.hdsx.appservice.mongo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的配置类
 */

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Value("${swagger2.package}")
    private String basePackage;

    @Value("${spring.application.name}")
    private String title;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "梁铖城",
                "https://github.com/liangchengcheng?tab=repositories",
                "1038127753@qq.com");

        return new ApiInfoBuilder()
                .title(title.toUpperCase() + " RESTFUL API")
                .description(title.toUpperCase() + " RESTFUL API详情(MONGO演示等相关操作)")
                .contact(contact)
                .version("1.0.0")
                .build();
    }

}
