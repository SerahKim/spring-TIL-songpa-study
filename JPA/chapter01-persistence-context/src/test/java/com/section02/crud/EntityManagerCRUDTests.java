package com.section02.crud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EntityManagerCRUDTests {

    private EntityManagerCRUD crud;

    @BeforeEach
    void initManager() {
        this.crud = new EntityManagerCRUD();
    }

    @DisplayName("메뉴 코드로 메뉴 조회 테스트")
    @ParameterizedTest
    @CsvSource({"1,1", "2,2", "3,3"}) // menuCode = 1, 2, 3 / expected = 1, 2, 3 => 즉, 테스트 3번 시행
    void testFindMenuByMenuCode(int menuCode, int expected) {

        // when
        Menu foundMenu = crud.findMenuByMenuCode(menuCode);

        // then
        // 예상하는 menuCode와 foundMenu의 menuCode를 비교
        assertEquals(expected, foundMenu.getMenuCode());
        System.out.println("foundMenu = " + foundMenu);
    }

    private static Stream<Arguments> newMenu() {
        return Stream.of(
                Arguments.of(
                        "신메뉴",
                        3500,
                        4,
                        "Y"
                )
        );
    }

    @DisplayName("새로운 메뉴 추가 테스트")
    @ParameterizedTest
    @MethodSource("newMenu") // newMenu 메소드가 반환하는 값을 테스트의 파라미터로 사용하겠다는 어노테이션
    void testRegist(String menuName, int menuPrice, int categoryCode, String orderableStatus) {

        // when
        Menu newMenu = new Menu(menuName, menuPrice, categoryCode, orderableStatus);
        Long count = crud.saveAndReturnAllCount(newMenu);

        // then
        assertEquals(23, count);
    }

    @DisplayName("메뉴 이름 수정 테스트")
    @ParameterizedTest
    @CsvSource("1, 변경된 이름")
    void testModifyMenuName(int menuCode, String menuName) {

        // when
        Menu modifiedMenu = crud.modifyMenuName(menuCode, menuName);

        // then
        assertEquals(menuName, modifiedMenu.getMenuName());
    }

    @DisplayName("메뉴 삭제 테스트")
    @ParameterizedTest
    @ValueSource(ints = {24})
    void testRemoveMenu(int menuCode) {
        // when
        Long count = crud.removeAndReturnAllCount(menuCode);

        // then
        assertEquals(22, count);
    }

}
