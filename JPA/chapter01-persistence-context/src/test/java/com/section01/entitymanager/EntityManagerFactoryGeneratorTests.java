package com.section01.entitymanager;

import com.section02.crud.Menu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class EntityManagerFactoryGeneratorTests {

    @Test
    @DisplayName("엔티티 매니저 팩토리 생성 확인")
    // 테스트코드에서는 접근 제한자 생략가능 (default로 설정되어 있음)
    void testGenerateEntityManagerFactory() {
        // given

        // when
        EntityManagerFactory factory = EntityManagerFactoryGenerator.getInstance();

        // then
        assertNotNull(factory);
    }

    @Test
    @DisplayName("엔티티 매니저 팩토리 싱글톤 인스턴스 확인")
    void testIsEntityManagerFactorySingletonInstance() {
        // given

        // when
        EntityManagerFactory factory1 = EntityManagerFactoryGenerator.getInstance();
        EntityManagerFactory factory2 = EntityManagerFactoryGenerator.getInstance();

        // then
        assertEquals(factory1, factory2);
    }

    @Test
    @DisplayName("엔티티 매니저 생성 확인")
    void testGeneratorEntityManager() {
        // given

        // when
        EntityManager entityManager = EntityManagerGenerator.getInstance();

        // then
        assertNotNull(entityManager);
    }

    @Test
    @DisplayName("엔티티 매니저 스코프 확인")
    void testEntityManagerLifeCycle() {
        // given

        // when
        EntityManager entityManager1 = EntityManagerGenerator.getInstance();
        EntityManager entityManager2 = EntityManagerGenerator.getInstance();

        // then
        assertNotEquals(entityManager1, entityManager2);
    }
}
