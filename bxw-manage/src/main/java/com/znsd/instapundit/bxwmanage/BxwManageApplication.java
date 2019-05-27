package com.znsd.instapundit.bxwmanage;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class BxwManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BxwManageApplication.class, args);
    }

}
