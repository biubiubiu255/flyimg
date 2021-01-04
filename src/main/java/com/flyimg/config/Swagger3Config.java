package com.flyimg.config;

import com.flyimg.controller.page.UserController;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

@Configuration
public class Swagger3Config {

    //http://127.0.0.1:8088/swagger-ui/index.html

    //Predicate<RequestHandler> selector1 = RequestHandlerSelectors.basePackage("com.share.modules.user.controller");


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //.apis(RequestHandlerSelectors.basePackage("com.flyimg.controller.page"))
                .paths(PathSelectors.any())
                .build();
    }





    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("凌风企业自建oss开发文档")
                .description("迅捷而又小巧的自建图床服务器，使用springboot+动态缓存实现的高效缓存服务")
                .contact(new Contact("lf tim", "http://www.lfscrm.com", "母鸡呀"))
                .version("1.0")
                .build();
    }
}


