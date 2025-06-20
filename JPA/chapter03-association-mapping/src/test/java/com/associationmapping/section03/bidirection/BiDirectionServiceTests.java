package com.associationmapping.section03.bidirection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BiDirectionServiceTests {

    @Autowired
    private BiDirectionService biDirectionService;

    // 진짜 연관관곈는 즉시로딩
    @DisplayName("양방향 연관 관계 매핑 조회(연관관계의 주인)")
    @Test
    void biDirectionFindTest1() {
        // given
        int menuCode = 10;

        // when
        Menu foundMenu = biDirectionService.findMenu(menuCode);

        // then
        assertEquals(menuCode, foundMenu.getMenuCode());
    }

    // 가짜 연관관계는 지연로딩
    @DisplayName("양방향 연관 관계 매핑 조회(연관관계의 주인 아님")
    @Test
    void BiDirectionRingTesst2() {
        // given
        int categoryCode = 10;

        // when
        Category foundCategory = biDirectionService.findCategory(categoryCode);

        // then
        assertEquals(categoryCode, foundCategory.getCategoryCode());

    }
}
