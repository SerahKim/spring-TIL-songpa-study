package com.springdatajpa.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        /* setter 메소드 미사용 시 ModelMapper가
         * private 필드끼리 자동 매핍될 수 있도록 설정
         * */
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldAccessLevel(
                        org.modelmapper.config.Configuration.AccessLevel.PRIVATE // private 필드에 접근 가능
                )
                .setFieldMatchingEnabled(true); // 필드 이름이 같으면 Entity와 DTO를 매칭시켜 자동으로 변환

        return modelMapper;
    }
}
