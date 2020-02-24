package com.lin.util.config;

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
 * Swagger2 Config
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * Create rest api docket.
     *
     * @return the docket
     * @author : fangkuaiIt / 2019-01-05
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("v1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lin.aijia"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Api info api info.
     *
     * @return the api info
     * @author : fangkuaiIt / 2019-01-05
     */
    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("AiJia RESTful APIs")
                .contact(new Contact("aijia", "", "1239717246@qq.com"))
                .description("AiJia接口文档")
                .version("1.0")
                .license("The Apache License, Version 2.0")
                .build();
    }
}
