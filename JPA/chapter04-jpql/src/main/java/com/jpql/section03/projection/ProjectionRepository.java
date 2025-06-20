package com.jpql.section03.projection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ProjectionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Menu> singleEntityProjection() {
        String jpql = "SELECT m FROM Section03Menu m";

        return entityManager.createQuery(jpql, Menu.class).getResultList();
    }

    public List<Object[]> scalaTypeProjection() {
        String jpql = "SELECT c.categoryCode, c.categoryName FROM Section03Category c";

        return entityManager.createQuery(jpql).getResultList();
    }

    public List<CategoryInfo> newCommandProjection() {
        // 일반 클래스로 매핑 (필드 값을 가지는 생성자로)
        String jpql = "SELECT new com.jpql.section03.projection.CategoryInfo(c.categoryCode, c.categoryName)"
                + "FROM Section03Category c";

        return entityManager.createQuery(jpql, CategoryInfo.class).getResultList();
    }

}
