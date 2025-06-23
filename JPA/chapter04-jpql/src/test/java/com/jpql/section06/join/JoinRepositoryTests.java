package com.jpql.section06.join;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JoinRepositoryTests {

    @Autowired
    private JoinRepository joinRepository;

    @DisplayName("INNER 조인 테스트")
    @Test
    void testSelectByInnerJoin() {
        List<Menu> menuList = joinRepository.selectByInnerJoin();

        assertNotNull(menuList);
    }

    @DisplayName("OUTER 조인 테스트")
    @Test
    void testSelectByOuterJoin() {
        List<Object[]> menuList = joinRepository.selectByOuterJoin();

        assertNotNull(menuList);

        menuList.forEach(
        row -> {
                for (Object column : row) {
                    System.out.print(column);
                }
                System.out.println();
            }
        );
    }

    @DisplayName("컬렉션 조인 테스트")
    @Test
    void testSelectByCollectionJoin() {
        List<Object[]> categoryList = joinRepository.selectByCollectionJoin();

        assertNotNull(categoryList);

        categoryList.forEach(
           row -> {
             for (Object column : row) {
                 System.out.print(column);
             }
             System.out.println();
           }
        );
    }

    @DisplayName("FETCH 조인 테스트")
    @Test
    void testSelectByFetchJoin() {

        // 즉시로딩되어 카테고리도 같이 참조하여 쿼리문을 조회한다.
        List<Menu> menuList = joinRepository.selectByFetchJoin();

        assertNotNull(menuList);
    }
}
