package com.znsd.instapundit.bxwapp;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class BxwAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BxwAppApplication.class, args);
    }
}
