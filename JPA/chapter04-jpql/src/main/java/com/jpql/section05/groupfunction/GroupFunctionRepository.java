package com.jpql.section05.groupfunction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupFunctionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public long countMenuOfCategory(int categoryCode) {
        String jpql = "SELECT COUNT(m.menuCode) FROM Section05Menu m WHERE m.categoryCode = :categoryCode";

        long countOfMenu = entityManager.createQuery(jpql, Long.class)
                .setParameter("categoryCode", categoryCode)
                .getSingleResult();

        return countOfMenu;
    }

    public Long otherWithNoReuslt(int categoryCode) {
        String jpql = "SELECT SUM(m.menuPrice) FROM Section05Menu m WHERE m.categoryCode = :categoryCode";

        /*
        COUNT 외의 다른 그룹함수는 결과 값이 없을 경우 Null이 반환되기 때문에 기본 자료형으로 다룰 경우 문제가 생긴다.
        자동 박싱처리 시 null은 박싱 처리를 할 수 없기 때문이다.
        따라서, 이를 해결하기 위해 Long과 같이 Wrapper 클래스 자료형으로 다루어주어야 한다.
        */
        Long sumOfMenu = entityManager.createQuery(jpql, Long.class)
                .setParameter("categoryCode", categoryCode)
                .getSingleResult();

        return sumOfMenu;
    }
    public List<Object[]> selectByGroupByHaving(long minPrice) {
        String jpql = "SELECT m.categoryCode, SUM(m.menuPrice) FROM Section05Menu m " +
                "GROUP BY m.categoryCode " +
                "HAVING SUM(m.menuPrice) >= :minPrice";

        List<Object[]> sumPriceOfCategoryList = entityManager.createQuery(jpql)
                .setParameter("minPrice", minPrice)
                .getResultList();

        return sumPriceOfCategoryList;

    }
}
