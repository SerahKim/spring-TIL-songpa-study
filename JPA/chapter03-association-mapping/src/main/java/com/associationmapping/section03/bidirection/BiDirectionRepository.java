package com.associationmapping.section03.bidirection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BiDirectionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Menu findMenu(int menuCode) {
        return entityManager.find(Menu.class, menuCode);
    }

    public Category findCategory(int categoryCode) {
        return entityManager.find(Category.class, categoryCode);
    }
}
