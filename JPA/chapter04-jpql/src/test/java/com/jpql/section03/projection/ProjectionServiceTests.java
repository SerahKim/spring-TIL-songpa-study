package com.jpql.section03.projection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.AssertionsKt.assertNotNull;

@SpringBootTest
public class ProjectionServiceTests {

    @Autowired
    private ProjectionService projectionService;

    @Autowired
    private ProjectionRepository projectionRepository;

    @DisplayName("단일 엔티티 프로젝션")
    @Test
    void testSingleEntityProjection() {
        List<Menu> menuList = projectionService.singleEntityProjection();

        assertNotNull(menuList);
    }

    @DisplayName("스칼라 타입 프로젝션")
    @Test
    void testScalarTypeProjection() {
        List<Object[]> categoryList = projectionRepository.scalaTypeProjection();

        assertNotNull(categoryList);

        categoryList.forEach(
                row -> {
                for(Object column : row) {
                    System.out.print(column + " ");
                }
                System.out.println();
            }
        );
    }

    @DisplayName("new 명령어를 활용한 프로젝션")
    @Test
    void testNewCommandProjection() {
        List<CategoryInfo> categoryInfoList = projectionRepository.newCommandProjection();

        assertNotNull(categoryInfoList);

        categoryInfoList.forEach(System.out::println);
    }
}
