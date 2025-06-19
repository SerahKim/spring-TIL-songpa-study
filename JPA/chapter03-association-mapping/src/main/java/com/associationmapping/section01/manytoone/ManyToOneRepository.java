package com.associationmapping.section01.manytoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToOneRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Menu find(int menuCode) {
        return entityManager.find(Menu.class, menuCode);
    }

    public String findCategoryName(int menuCode) {
        // jpql은 테이블 중심이 아닌 엔티티 중심, 별칭 필수 작성
        // m.category : menu_and_category 엔티티의 category를 기준으로 조인하겠다
        // :menuCode : 전달할 파라미터
        String jpql = "SELECT c.categoryName FROM menu_and_category m JOIN m.category c WHERE m.menuCode = :menuCode";

        return entityManager.createQuery(jpql, String.class)
                .setParameter("menuCode", menuCode)
                .getSingleResult();
    }

    public void regist(Menu menu) {
        entityManager.persist(menu);
    }
}
