package com.travelsnotes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2     //开启Swagger
public class SwaggerConfig {

    @Bean //配置Swagger的Bean实例
    public Docket docket(Environment environment){
        //设置要使用Swagger的环境
        //Profiles profiles=Profiles.of("dev","test");
        // 获取是否在指定环境种 传入enable
     //   boolean isProfiles = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .enable(true)//  enable 是否启动Swagger false 禁止在服务器访问Swagger 生产环境开启
                .select()
                //RequestHandlerSelectors 配置要扫描接口的方式
                //basePackage:指定要扫描的包 一般用这个 basePackage("com.zeng.swagger.controller")
                .apis(RequestHandlerSelectors.basePackage("com.travelsnotes.controller"))
                //paths 过滤什么路径
                // .paths(PathSelectors.ant("/zeng/**"))
                .build();
    }
    //配置Swagger的配置信息=apiInfo
    private ApiInfo getApiInfo(){
        Contact contact = new Contact("Travels-notes的Swagger文档", "", "");

        return  new ApiInfo(
                "Travels-notes的Swagger文档",
                "你尽管努力剩下的交给天意",
                "v1.0",
                "",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>());
    }
}