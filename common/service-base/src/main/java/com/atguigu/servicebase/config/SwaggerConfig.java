package com.atguigu.servicebase.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // https://mp.weixin.qq.com/s/0-c0MAgtyOeKx6qzmdUG0w
    private static Contact contact = new Contact("szf","http://localhost/","2802612334@qq.com");

    private static ApiInfo apiInfo = new ApiInfo(
            "网站-课程中心API文档",
            "本文档描述了课程中心微服务接口定义",
            "0.0.1-SNAPSHOT",                                                   // 版本
            "http://localhost:8001/swagger-ui.html",                    // 组织链接
            contact,                                                                    // 联系人信息
            "Apach 2.0 许可",                                                    // 许可
            "许可链接",                                                        // 许可链接
            new ArrayList<>()
    );

    @Bean
    public Docket docket(Environment environment){
        // 1.设置显示swagger的环境，在dev和test环境下显示swagger文档信息
        Profiles of = Profiles.of("dev", "test");

        // 2.判断当前环境是否处于该环境
        boolean flag = environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .groupName("webApi")
                .enable(flag)                                                           // 在指定环境中开启swagger
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))      // 管理请求不生成swagger文档
                .apis(RequestHandlerSelectors.basePackage("com.atguigu"))               // 在该包下的方法生成swagger的文档
                .build();
    }
}