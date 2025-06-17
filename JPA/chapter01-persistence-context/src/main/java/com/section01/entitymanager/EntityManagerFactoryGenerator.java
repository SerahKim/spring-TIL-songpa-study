package com.section01.entitymanager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryGenerator {

    // EntityManagerFactory은 싱글톤으로 관리되는 것이 효율적이기 떄문에 static 필드 생성
    private static EntityManagerFactory factory
            = Persistence.createEntityManagerFactory("jpatest");

    // EntityManagerFactory은 싱글톤으로 관리되어야 하기 때문에 굳이 다른 곳에서 객체를 만들 필요 없다. 접근제한자 private으로 설정
    private EntityManagerFactoryGenerator() {}

    // 대신 EntityManagerFactory울 쓰고 싶으면 EntityManagerFactoryGenerator의 static 메소드인 getInstance()를 통해 사용한다.
    public static EntityManagerFactory getInstance() {
        return factory;
    }


}
