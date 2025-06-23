package com.jpql.section06.join;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JoinRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Menu> selectByInnerJoin() {
        // 이미 메뉴의 category로 join을 하기 때문에 ON이 필요없다.
        // category를 조회하기 위해 category의 개수만큼 쿼리문이 발생한다.
        // N+1 문제 : 프로젝션이 엔티티인 경우 엔티티인 Menu 조회 후 조인한 엔티티인 category를 조회하는 지연 로딩이 발생하는 것
        String jpql = "SELECT m FROM Section06Menu m JOIN m.category c";

        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();

        return menuList;
    }

    public List<Object[]> selectByOuterJoin() {
        String jpql = "SELECT m.menuName, c.categoryName FROM Section06Menu m " +
                "RIGHT JOIN m.category c " +
                "ORDER BY m.category.categoryCode";

        List<Object[]> menuList = entityManager.createQuery(jpql).getResultList();

        return menuList;
    }

    public List<Object[]> selectByCollectionJoin() {
        String jpql = "SELECT m.menuName, c.categoryName FROM Section06Category c " +
                "RIGHT JOIN c.menuList m " +
                "ORDER BY m.category.categoryCode";

        List<Object[]> categoryList = entityManager.createQuery(jpql).getResultList();

        return categoryList;
    }

    public List<Menu> selectByFetchJoin() {
        /*
        FETCH : JPQL에서 성능 최적화를 위해 제공하는 기능으로 연관된 엔티티나 컬렉션을 한번에 조회한다.
        지연 로딩이 아닌 즉시 로딩을 수행한다.
        */
        String jpql = "SELECT m FROM Section06Menu m JOIN FETCH m.category c";

        List<Menu> menuList = entityManager.createQuery(jpql, Menu.class).getResultList();

        return menuList;
    }


}
