package com.section03.properties;

import com.common.Beverage;
import com.common.Bread;
import com.common.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("section03/properties/product-info.properties")
public class ContextConfiguration {
    /*
    치환자 문법을 이용하여 properties에 저장된 Key를 입력하면 value에 해당하는 값을 꺼내온다.
    공백을 사용하면 값을 읽어오지 못하니 주의한다.
    : (콜론)을 사용하면 값을 읽어오지 못하는 경우 사용할 대체 값을 작성할 수 있다.
    */

    @Value("${bread.carpbread.name}")
    private String carpBreadName;

    @Value("${bread.carpbread.price}")
    private int carpBreadPrice;

    @Value("${beverage.milk.name}")
    private String milkName;

    @Value("${beverage.milk.price:0}")
    private int milkPrice;

    @Value("${beverage.milk.capacity:0}")
    private int milkCapacity;

    @Bean
    public Product carpBread() {
        return new Bread(carpBreadName, carpBreadPrice, new java.util.Date());
    }

    @Bean
    public Product milk() {
        return new Beverage(milkName, milkPrice, milkCapacity);
    }

    @Bean
    public Product water(@Value("${beverage.water.name}") String waterName,
                         @Value("${beverage.water.price}") int waterPrice,
                         @Value("${beverage.water.capacity}") int waterCapactity) {
        return new Beverage(waterName, waterPrice, waterCapactity);
    }
}
