package com.znsd.instapundit.bxwmanage.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${cbs.imagesPath}")
    private String mImagesPath;


    @Autowired
    private MyHandlerInterceptor baseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(baseInterceptor);
        // 拦截配置
        addInterceptor.addPathPatterns("/**").excludePathPatterns(Arrays.asList("/loginUser", "/index.html", "/c/**", "/login.html", "/", "/error", "/js/**", "/css/**", "/images/**", "/lib/**", "/fonts/**", "/**/edit*/**", "/**/add*", "/**/particulars*", "/**/play*","/pie/videotypelist"));

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/login.html").setViewName("login/login");
        registry.addViewController("/specialColumn/list").setViewName("specialColumn/list");
        registry.addViewController("/comment/list").setViewName("comment/list");
    }


//    /**
//     * 配置上传文件
//     * @return
//     */
//    @Bean
//    public MultipartConfigElement multipartConfigElement(){
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //文件最大KB,MB
//        factory.setMaxFileSize("1024MB");
//        //设置总上传数据总大小
//        factory.setMaxRequestSize("1024MB");
//        return factory.createMultipartConfig();
//    }


}