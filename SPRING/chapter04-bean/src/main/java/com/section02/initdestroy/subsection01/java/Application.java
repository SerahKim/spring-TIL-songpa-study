package com.section02.initdestroy.subsection01.java;

import com.common.Beverage;
import com.common.Bread;
import com.common.Product;
import com.common.ShopppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
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

        /*
        init 메소드는 빈 객체 생성 시점에 동작하므로 바로 확인할 수 있지만
        destroy 메소드는 빈 객체 소멸 시점에 동작하므로 컨테이너가 종료되지 않을 경우 확인할 수 없다.
        가비지 컬렉터가 해당 빈을 메모리에서 지울 때 destroy 메소드가 동작하게 되는데 메모리에서 지우기 전에 프로세스는 종료되기 때문이다.
        따라
        */
        ((AnnotationConfigApplicationContext) context).close();
    }
}
