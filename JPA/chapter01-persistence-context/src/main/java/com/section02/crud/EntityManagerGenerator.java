package com.section02.crud;

import com.section01.entitymanager.EntityManagerFactoryGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerGenerator {

    private static EntityManagerFactory factory
            = Persistence.createEntityManagerFactory("jpatest");

    private EntityManagerGenerator() {}

    public static EntityManager getInstance() {
        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();

        return factory.createEntityManager();
    }
}
