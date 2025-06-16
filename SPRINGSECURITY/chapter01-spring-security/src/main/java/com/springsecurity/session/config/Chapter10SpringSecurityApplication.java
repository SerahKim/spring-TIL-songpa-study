package com.springsecurity.session.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springsecurity.session")
public class Chapter10SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter10SpringSecurityApplication.class, args);
    }

}
