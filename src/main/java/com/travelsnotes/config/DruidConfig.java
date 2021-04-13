package com.travelsnotes.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    //将配置文件中的配置导入当前的bean中 前缀spring.datasource
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    //获取后台监控 web.xml
    public ServletRegistrationBean statViewServlet(){
        //配置访问页面 druid/*
        ServletRegistrationBean<StatViewServlet> bean=new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");

        Map<String,String> initParameters=new HashMap<>();
        initParameters.put("loginUsername","admin"); //用户名 前面的是固定的
        initParameters.put("loginPassword","123456");

        //允许谁可以访问
        initParameters.put("allow","");
        //禁止谁不能访问
        //initParameters.put("deny","");
        //后台需要有人登录，账号密码配置
        bean.setInitParameters(initParameters);       //设置初始化配置参数
        return bean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        //可以过滤那些请求
        filterRegistrationBean.addUrlPatterns("/*");
        //不过滤哪些请求 这些不进行统计
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
