package com.section02.initdestroy.subsection01.java;

import com.common.Beverage;
import com.common.Bread;
import com.common.Product;
import com.common.ShopppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ContextConfiguration {
    @Bean
    public Product carpBread() {
        return new Bread("붕어빵", 1000, new java.util.Date());
    }

    @Bean
    public Product milk() {
        return new Beverage("딸기 우유", 1500, 500);
    }

    @Bean
    public Product water() {
        return new Beverage("지리산암반수", 3000, 500);
    }

    @Bean
    @Scope("prototype")
    public ShopppingCart cart() {
        return new ShopppingCart();
    }

    // Bean이 초기화될 때 openShop 빈 동작
    @Bean(initMethod = "openShop", destroyMethod = "closeShop")
    public Owner owner() {
        return  new Owner();
    }
}
