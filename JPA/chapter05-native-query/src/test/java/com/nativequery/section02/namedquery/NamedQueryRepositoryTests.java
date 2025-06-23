package com.nativequery.section02.namedquery;

import com.nativequery.seciont02.namedquery.NamedQueryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class NamedQueryRepositoryTests {

    @Autowired
    private NamedQueryRepository namedQueryRepository;

    @DisplayName("NamedNaticeQuery를 이용한 조회 테스트")
    @Test
    void testSelectByNamedNativeQuery() {
        List<Object[]> categoryList
                = namedQueryRepository.selectByNamedNativeQuery();

        assertNotNull(categoryList);
        categoryList.forEach(
                row -> {
                    for(Object col : row) {
                        System.out.print(col + "/");
                    }
                    System.out.println();
                }
        );
    }

}
