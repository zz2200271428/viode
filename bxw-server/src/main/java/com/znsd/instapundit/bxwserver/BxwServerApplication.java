package com.znsd.instapundit.bxwserver;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDubboConfiguration
@EnableTransactionManagement
public class BxwServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BxwServerApplication.class, args);
    }
}
