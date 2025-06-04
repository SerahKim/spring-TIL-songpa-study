package com.section01.autowired.subsection03.setter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.section01");

        BookService bookService = context.getBean("bookServiceSetter", BookService.class);

        bookService.selectAllBooks().forEach(System.out::println);
    }
}
