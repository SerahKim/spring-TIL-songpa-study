package com.section01.scope.subsection01.singleton;

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

        // bean의 기본 스코프는 싱글톤으로 관리되고 있어, 변수명이 달라도 같은 카트를 공유하고 있다.
        System.out.println(cart1.hashCode());
        System.out.println(cart2.hashCode());

        /*
        스프링프레임워크에서 Bean의 기본 스코프는 singleton이다.
        singleton 스코프를 갖는 Bean 애플리케이션 내에서 유일한 인스턴스를 갖는다.
        이 예제에서 손님 두 명이 각각 쇼핑카트를 이용해서 상품을 담았을 때
        singleton으로 관리되는 cart는 두 손님이 동일한 카트에 물건을 담게 된다.
        */
    }
}
