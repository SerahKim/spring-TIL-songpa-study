package com.section03.properties;

import com.common.Beverage;
import com.common.Bread;
import com.common.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        Product carpBread = context.getBean("carpBread", Bread.class);
        System.out.println("carpBread = " + carpBread);

        Product milk = context.getBean("milk", Beverage.class);
        System.out.println("milk = " + milk);

        Product water = context.getBean("water", Beverage.class);
        System.out.println("water = " + water);
    }
}
