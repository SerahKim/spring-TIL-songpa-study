package com.jpql.section01.simple;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SimpleJPQLRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public String selectSingleMenuByTypedQuery() {
        String jpql = "SELECT m.menuName FROM Section01Menu as m WHERE m.menuCode = 8";
        // createQuery(사용할 jpql, 반환 받을 형식)
        TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
        // .getSingleResult() : 행이 하나만 조회될 때
        // .getListResult() : 리스트로 조회될 때
        String resultMenuName = query.getSingleResult();

        return resultMenuName;
    }

    public List<Menu> selectMultiRowByTypedQuery() {
        // 모든 행을 조회하고 싶다면 엔티티 이름을 넣어주면 된다.
        String jpql = "SELECT m FROM Section01Menu as m";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);
        List<Menu> resultMenuList = query.getResultList();

        return resultMenuList;
    }

    public List<Integer> selectUsingDistinct() {
        String jpql = "SELECT DISTINCT m.categoryCode FROM Section01Menu m";
        TypedQuery<Integer> query = entityManager.createQuery(jpql, Integer. class);
        List<Integer> resultCategoryList = query.getResultList();

        return resultCategoryList;


    }

    /* 11, 12 카테고리 코드를 가진 메뉴 목록 조회 */
    public List<Menu> selectUsingIn() {
        String jpql = "SELECT m FROM Section01Menu m WHERE m.categoryCode IN (11, 12)";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);
        List<Menu> resultMenuListByCategoryCode = query.getResultList();

        return resultMenuListByCategoryCode;
    }

    /* "마늘"이라는 문자열이 메뉴명에 포함되는 메뉴 목록 조회 */
    public List<Menu> selectUsingLike() {
        String jpql = "SELECT m FROM Section01Menu m WHERE m.menuName LIKE '%마늘%'";
        TypedQuery<Menu> query = entityManager.createQuery(jpql, Menu.class);
        List<Menu> resultMenuListByMenuName = query.getResultList();

        return resultMenuListByMenuName;
    }

}
