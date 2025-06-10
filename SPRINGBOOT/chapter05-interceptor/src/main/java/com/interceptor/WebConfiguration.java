package com.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    // 저장소에 StopWatchInteceptor 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new StopWatchInteceptor(new MenuService()))
                .addPathPatterns("/*") // 모든 핸들러 메소드를 가로챔
                /* static 하위의 정적 리소는 인터셉터가 적용되지 않도록 한다. */
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/js/**")
                /* /error로 포워딩 되는 경로도 제외해주어야 한다. */
                .excludePathPatterns("/error");
    }
}
