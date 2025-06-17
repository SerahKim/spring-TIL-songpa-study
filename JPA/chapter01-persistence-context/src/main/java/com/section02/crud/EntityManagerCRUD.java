package com.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EntityManagerCRUD {

    private EntityManager entityManager;

    /* 1. 특정 메뉴 코드로 메뉴 조회하는 기능 */
    public Menu findMenuByMenuCode(int menuCode) {
        entityManager = EntityManagerGenerator.getInstance();

        // find : pk 기준의 조회만 가능
        return entityManager.find(Menu.class, menuCode);
    }

    /* 2. 새로운 메뉴 저장 */
    public Long saveAndReturnAllCount(Menu newMenu) {
        entityManager = EntityManagerGenerator.getInstance();

        /*
        EntityManager가 관리하는 persistence context에 newMenu 저장
        실제 데이터베이스에 저장하는 것은 아니고 인메모리 상에 저장되는 것이기 때문에
        실제 DB에 저장하기 위해 transaction을 해줘야 한다.
        */
        EntityTransaction entityTransaction = entityManager.getTransaction();

        // begin과 commit 사이의 동작이 하나의 transaction으로 처리되기 때문에 DB에 저장된다.
        entityTransaction.begin();
        entityManager.persist(newMenu);
        entityTransaction.commit();

        return getCount(entityManager);
    }

    /* 메뉴 개수 조회하는 기능 */
    private Long getCount(EntityManager entityManager) {
        /* JPQL 문법 -> 나중에 별도의 챕터에서 다룰 예정 */
        return entityManager.createQuery("SELECT COUNT(*) FROM Section02Menu", Long.class).getSingleResult();
    }

    /* 3. 메뉴 이름 수정하기 */
    public Menu modifyMenuName(int menuCode, String menuName) {
        entityManager = EntityManagerGenerator.getInstance();

        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        foundMenu.setMenuName(menuName);
        transaction.commit();

        return foundMenu;
    }


}
