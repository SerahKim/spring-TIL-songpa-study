package com.section01.autowired.subsection01.field;

import com.section01.common.BookDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        /* 컴포넌트 스캰 기능을 활성화시키는 또 다른 방법이다. */
        ApplicationContext context = new AnnotationConfigApplicationContext("com.section01");

        BookService bookService = context.getBean("bookServiceField", BookService.class);

        /* 전체 도서 목록 조회 후 출력 확인 */
        List<BookDTO> books = bookService.selectAllBooks();
        for (BookDTO book : books) {
            System.out.println("book = " + book);
        }
    }
}
