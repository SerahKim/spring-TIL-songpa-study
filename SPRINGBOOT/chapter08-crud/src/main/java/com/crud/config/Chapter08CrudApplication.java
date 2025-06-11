package com.crud.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.crud")
@MapperScan(basePackages = "com.crud", annotationClass = Mapper.class) // Mapper로 등록되어 있는 모든 Mapper 클래스를 넘긴다.
public class Chapter08CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter08CrudApplication.class, args);
    }

}
