package com.mapping.section02.embeded;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootTest
public class BookRegistServiceTests {

    @Autowired
    private BookRegistService bookRegistService;

    private static Stream<Arguments> getBook() {
        return Stream.of(
                Arguments.of(
                        " 자바 ORM 표준 JPA 프로그래밍",
                        "김영한",
                        "에이콘",
                        LocalDate.now(),
                        43000,
                        0.1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getBook")
    void testCreateEmbeddedPriceOfBook(
            String bookTitle, String author, String publisher,
            LocalDate publishedDate, int regularPrice, double discountRate
    ) {
        //given
        BookRegistDTO newBook = new BookRegistDTO(
                bookTitle,
                author,
                publisher,
                publishedDate,
                regularPrice,
                discountRate
        );

        //when
        //then
        Assertions.assertDoesNotThrow(
                () -> bookRegistService.registBook(newBook)
        );
    }
}
