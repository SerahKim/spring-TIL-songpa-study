package com.springsecurity.session.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.springsecurity.session", annotationClass = Mapper.class)
public class MybatisConfiguration {
}
