package com.section02.initdestroy.subsection02.annotation;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Owner {

    /* init method와 같은 설정 어노테이션 */
    @PostConstruct
    public void openShop() {
        System.out.println("사장님이 가게 문을 열었습니다.이제 쇼핑을 하실 수 있습니다.");
    }

    /* destroy method와 같은 설정 어노테이션 */
    @PreDestroy
    public void closeShop() {
        System.out.println("사장님이 가게 문을 닫았습니다. 이제 쇼핑을 하실 수 없습니다.");
    }
}
