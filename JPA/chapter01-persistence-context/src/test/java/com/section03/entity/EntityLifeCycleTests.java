package com.section03.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class EntityLifeCycleTests {

    private EntityLifeCycle lifeCycle;

    @BeforeEach
    void setup() {
        this.lifeCycle = new EntityLifeCycle();
    }

    @DisplayName("비영속 테스트")
    @ParameterizedTest
    @ValueSource(ints = {2})
    void testTransient(int menuCode) {
        // when
        Menu foundMenu = lifeCycle.findMenuByMenuCode(menuCode);

        // 이 객체는 직접 만들어준 객체이므로 P.C에서 관리되지 않는다. (비영속 상태)
        Menu newMenu = new Menu(
                foundMenu.getMenuCode(),
                foundMenu.getMenuName(),
                foundMenu.getMenuPrice(),
                foundMenu.getCategoryCode(),
                foundMenu.getOrderableStatus()
        );

        EntityManager entityManager = lifeCycle.getManagerInstance();

        // then
        assertNotEquals(foundMenu, newMenu);
        // 영속성, entityManager가 관리하는 P.C 안에 foundMenu가 있는지 확인하는 테스트 구문
        assertTrue(entityManager.contains(foundMenu));
        // 비영속성
        assertFalse(entityManager.contains(newMenu));
    }

    @DisplayName("다른 엔티티 메니저가 관리하는 엔티티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3})
    void testManagedOtherEntityManager(int menuCode) {
        // when
        // menu1, menu2는 다른 객체
        // 서로 다른 Persistence Context에서 관리된다.
        Menu menu1 = lifeCycle.findMenuByMenuCode(menuCode);
        Menu menu2 = lifeCycle.findMenuByMenuCode(menuCode);

        // then
        assertNotEquals(menu1, menu2);
    }

    @DisplayName("같은 엔티티 메니저가 관리하는 엔티티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3})
    void testManagedSameEntityManager(int menuCode) {

        // given
        EntityManager entityManager = EntityManagerGenerator.getInstance();

        // when
        // 영속성 컨텍스트에 없기 때문에 P.C에 값을 등록하고 반환한다.
        Menu menu1 = entityManager.find(Menu.class, menuCode);
        // 이미 영속성 컨텍스트에 값이 있기 떄문에 영속성 컨텍스트에서 그 값을 반환한다.
        Menu menu2 = entityManager.find(Menu.class, menuCode);
        // 따라서 select 구문은 한번만 발생한다.

        // then
        assertEquals(menu1, menu2);
    }

    @DisplayName("준영속화 detach 테스트")
    @ParameterizedTest
    @CsvSource("11, 1000")
    void testDetachEntity(int menuCode, int menuPrice) {
        // given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        // when
        entityTransaction.begin();
        // DB에서 조회
        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        // detach : 특정 엔티티만 준영속 상태(영속성 컨텍스트가 관리하던 객체를 관리하지 않는 상태)로 만든다.
        entityManager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);
        // flush : 영속성 컨텍스트의 상태를 DB에 내보낸다. commit하지 않은 상태이므로 rollback이 가능하다.
        // commit : flush가 내장되어 있고 DB에 적용되는 상태, rollback이 불가능하다.
        entityManager.flush();
//        entityManager.commit(); // 관리하고 있는 foundMenu가 없기 떄문에 바뀐 값이 저장되지 않는다.

        // then
        // detach 됐기 때문에 P.C에 없어 다시 DB에서 조회
        // 따라서 asserNotEquals(1000, 10000) 이렇게 될 것이다.
        assertNotEquals(menuPrice, entityManager.find(Menu.class, menuCode).getMenuPrice());

        entityTransaction.rollback();
    }

    @DisplayName("준영속화 detach 후 다시 영속화 테스트")
    @ParameterizedTest
    @CsvSource("11, 1000")
    void testDetachAndMergeEntity(int menuCode, int menuPrice) {
        // given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        // when
        entityTransaction.begin();
        // 첫번째 DB 조회
        Menu foundMenu = entityManager.find(Menu.class, menuCode);
        entityManager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);
        // 두번째 DB 조회, merge하기 위해 DB 조회 후
        // 값을 업데이트 해준다.
        entityManager.merge(foundMenu);
        entityManager.flush();

        // then
        assertEquals(menuPrice, entityManager.find(Menu.class, menuCode).getMenuPrice());
        entityTransaction.rollback();
    }

    @DisplayName("detach 후 merge한 데이터 update 테스트")
    @ParameterizedTest
    @CsvSource("11, 전복죽")
    void testDetachAndMerge(int menuCode, String menuName) {
        // given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        // when
        entityManager.detach(foundMenu);
        foundMenu.setMenuName(menuName);
        // P.C에서 관리하고 있는 해당 menuCode가 없기 때문에(detach 때문) DB에서 값을 찾아오고 해당 메뉴 코드로 P.C에서 관리된다.
        Menu refoundMenu = entityManager.find(Menu.class, menuCode); // 현재 정어리 빙수
        // refoundMenu 객체를 생성하면서 P.C에 이미 같은 menuCode가 등록되어 있다.
        // 이 때 merge할 경우 foundMenu의 변경 사항(전복죽)으로 덮어씌워지게 된다.
        entityManager.merge(foundMenu);

        // then
        // 따라서 둘 다 전복죽으로 같은 값이 된다.
        assertEquals(menuName, refoundMenu.getMenuName());

    }

    @DisplayName("준영속화 clear 테스트")
    @ParameterizedTest
    @ValueSource(ints = {6})
    void testClearPersistenceContext(int menuCode) {
        // given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        // when
        /*
        clear : 영속성 컨텍스트를 초기화 한다
        -> 영속성 컨텍스트 내의 모든 엔티티를 준영속화 시킨다.
        -> 1차 캐시가 비워진다.즉, foundMenu는 여전히 메모리에는 있지만 JPA가 관리하지 않는 객체가 되는 것
        */
        entityManager.clear();

        // then
        /*
        준영속화를 시켰기 때문에 foundMenu와는 다른 값이다.
        준영속화를 시킨 이후(clear()) find()를 다시 호출하면 JPA 1차 캐시에 없기 때문에 DB 새로 조회하고,
        새로운 영속 상태의 객체를 만들어서 1차 캐시에 등록하고 반환한다.
        즉, expectedMenu는 새로운 객체가 되는 것
        만약, clear()를 하지 않았다면 같은 객체를 캐시해서 재사용하기 때문에 foundMenu와 expectedMenu는 동일하게 되는 것
        */
        Menu expectedMenu = entityManager.find(Menu.class, menuCode);

        // 값은 같지만 주소값이 다르다.
        System.out.println("foundMenu = " + foundMenu.hashCode());
        System.out.println("expectedMenu = " + expectedMenu.hashCode());

        assertNotEquals(foundMenu, expectedMenu);
    }

    @DisplayName("준영속화 close 테스트")
    @ParameterizedTest
    @ValueSource(ints = {6})
    void testClosePersistenceContext(int menuCode) {
        // given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        // when
        // close : 영속성 컨텍스트를 종료한다.
        entityManager.close();

        // then
        // entityManager.close()로 인해 entityManager가 종료되어 버리고 관리 목록이 사라져버린다.
        // 따라서 entityManager를 통해 find를 수행하려고 할 떄 에러가 발생하게 된다.
        assertThrows(
                IllegalStateException.class,
                () -> entityManager.find(Menu.class, menuCode));
    }

    @DisplayName("영속성 엔티티 삭제 remove 테스트")
    @ParameterizedTest
    @ValueSource(ints = {6})
    void testRemoveEntity(int menuCode) {
        // given
        EntityManager entityManager = EntityManagerGenerator.getInstance();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Menu foundMenu = entityManager.find(Menu.class, menuCode);

        // when
        entityTransaction.begin();
        // remove : 엔티티를 영속성 컨텍스트 및 데이터베이스에서 삭제
        // 단, 트랜잭션을 제어하지 않으면 데이터베이스에 영구 반영되지는 않는다.
        // 트랜잭션을 커밋하는 순간 영속성 컨텍스트에서 관리하는 엔티티 객체가 데이터베이스에 반영된다.
        entityManager.remove(foundMenu);
        // flush : 영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화하는 작업 (동기화는 되어있지만 저장은 안되어 있는 상태)
        entityManager.flush();

        // then
        // flush를 통해 remove한 것이 DB에 반영 (저장은 아닌)된 상태이기 떄문에 값을 찾을 수 없게 된다.
        Menu refoundMenu = entityManager.find(Menu.class, menuCode);
        assertNull(refoundMenu);
        entityTransaction.rollback();
    }

}
