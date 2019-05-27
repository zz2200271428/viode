package com.znsd.instapundit.bxwapp.config;

import com.znsd.instapundit.bxwapp.config.resolver.UserIDArgumentsResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Autowired
    private MyHandler myHandler;

    @Autowired
    private UserIDArgumentsResolver userIDArgumentsResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(myHandler);
        interceptorRegistration.addPathPatterns("/**").excludePathPatterns(Arrays.asList("/login",
                "/index.html", "/register/*register", "/appuser/login", "/appuser/code", "/appuser/*pwd", "/c/**", "/login.html",
                "/", "/error", "/js/**", "/css/**", "/images/**", "/img/**", "/lib/**", "/fonts/**", "/**/edit*/**"));

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login/login");
        registry.addViewController("/login").setViewName("login/login");
        registry.addViewController("/login.html").setViewName("login/login");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userIDArgumentsResolver);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("1024MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("1024MB");
        return factory.createMultipartConfig();
    }
}
