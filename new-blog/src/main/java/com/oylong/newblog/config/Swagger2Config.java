package com.oylong.newblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: oyLong
 * @Description:swagger的配置类
 * @Date: 14:53 2020/4/6
*/
@Configuration
@EnableSwagger2
public class Swagger2Config {
    /**
     * @Author: oyLong
     * @Description: 配置swagger2的apiInfo和扫描路径
     * @Date: 15:16 2020/4/6
     * @Param: []
     * @Return: springfox.documentation.spring.web.plugins.Docket
    */
    @Bean
    public Docket docket() {
        ParameterBuilder token = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        token.name("token").description("token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(token.build());    //根据每个方法名也知道当前方法在设置什么参数


        return  new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .globalOperationParameters(pars)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oylong.newblog.controller"))
                .build();
    }


    ApiInfo apiInfo() {
        return new ApiInfo(
                "MyBlog Api Documentation",
                "MyBlog Api Documentation",
                "1.0",
                "https://www.oylong.com",
                new Contact("OyLong", "https://www.oylong.com", "ouyanglong721@gmail.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
