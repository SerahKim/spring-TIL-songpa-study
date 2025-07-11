package com.associationmapping.section01.manytoone;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class ManyToOneServiceTests {

    @Autowired
    private ManyToOneService manyToOneService;

    @DisplayName("N:1 연관 관계 조회 테스트")
    @Test
    void manyToOneFindTest() {

        // given
        int menuCode = 10;

        // when
        Menu foundMenu = manyToOneService.findMenu(menuCode);

        // then
        Category category = foundMenu.getCategory();

        assertNotNull(category);
    }

    @DisplayName("N:1 얀관관계 객체 지향 쿼리(JPQL)을 이용한 조회테스트")
    @Test
    void manyToOneJpqlFindTest() {
        // given
        int menuCode = 10;

        // when
        String categoryName = manyToOneService.findCategoryNameByJpql(menuCode);

        // then
        assertNotNull(categoryName);
        System.out.println("Category : " + categoryName);
    }

    private static Stream<Arguments> getMenuInfo() {
        return Stream.of(
                Arguments.of(123, "돈까스 스파게티", 1500, 123, "퓨전분식", "Y")
        );
    }

    @DisplayName("N:1 연관관계 객체 삽입 테스트")
    @ParameterizedTest
    @MethodSource("getMenuInfo")
    void manyToOneInsert(int menuCode, String menuName, int menuPrice, int CategoryCode, String categoryName, String orderableStatus){
        // given
        MenuDTO menuInfo = new MenuDTO(
                menuCode,
                menuName,
                menuPrice,
                new CategoryDTO(
                        CategoryCode,
                        categoryName,
                        null
                ),
                orderableStatus
        );

        // when
        // then
        assertDoesNotThrow(
                () -> manyToOneService.registMenu(menuInfo)
        );
    }
}
