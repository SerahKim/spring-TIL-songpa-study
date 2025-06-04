package com.section02.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 우선적으로 동작시키고 싶은 클래스 지정 어노테이션
public class Pikachu implements Pokemon{

    @Override
    public void attack() {
        System.out.println("피카츄 백만 볼트~~~");
    }
}
