package com.section01.scope.subsection02.prototype;

import com.common.Beverage;
import com.common.Bread;
import com.common.Product;
import com.common.ShopppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        /*
        bean scope
        스프링 빈이 생성될 때 생성되는 인스턴스의 범위를 의미한다. 스프링에서는 다양한 Bean scope를 제공한다.
        - singleton : 하나의 인스턴스만을 생성하고, 모든 빈이 해당 인스턴스를 공유하며 사용한다.
        - prototype : 매번 새로운 인스턴스를 생성한다.
        - request : HTTP 요청을 처리할 때마다 새로운 인스턴스를 생성하고, 요청 처리가 끝나면 인스턴스를 폐기한다.
        - session : HTTP 세션 당 하나의 인스턴스를 생성하고, 세션이 종료되면 인스턴스를 폐기한다.
        - globalSession : 전역 세션 당 하나의 인스턴스를 생성하고, 전역 세션이 종료되면 인스턴스를 폐기한다.
        */

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

//        String[] beanNames = context.getBeanDefinitionNames();
//        for (String beanName : beanNames) {
//            System.out.println("beanName = " + beanName);
//        }

        /* 붕어빵, 딸기우유, 지리산 암반수 등의 빈 객체를 반환 받는다. */
        Product carpBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Beverage.class);
        Product water = context.getBean("water", Beverage.class);

        /* 첫 번째 손님이 쇼핑 카트를 꺼낸다. */
        ShopppingCart cart1 = context.getBean("cart", ShopppingCart.class);
        cart1.addItem(carpBread);
        cart1.addItem(milk);

        System.out.println("cart1에 담긴 상품 = " + cart1.getItems());

        /* 두 번쨰 손님이 쇼핑 카트를 꺼낸다. */
        ShopppingCart cart2 = context.getBean("cart", ShopppingCart.class);
        cart2.addItem(water);

        System.out.println("cart2에 담긴 상품 = " + cart2.getItems());

        // @Scope("prototype")을 통해 ShopppingCart를 만들 때마다 새로운 인스턴스를 생성한다.
        System.out.println(cart1.hashCode());
        System.out.println(cart2.hashCode());
    }
}
