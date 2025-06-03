package com.section01.common;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
- @repository는 @Component의 세분화 어노테이션의 한 종류로 DAO 타입의 객체에 사용한다.
- annotation은 인터페이스가 아닌 구현되고 있는 클래스 쪽에 붙여줘야 한다.
*/
@Repository("bookDAO")
public class BookDAOImpl implements BookDAO{
    private final Map<Integer, BookDTO> bookList;

    public BookDAOImpl() {
        bookList = new HashMap<>();
        bookList.put(1, new BookDTO(1, 12345, "자바의 정석", "남궁성", "도우출판", new java.util.Date()));
        bookList.put(2, new BookDTO(2, 65432, "칭찬은 고래도 춤추게 한다", "고래", "고래출판", new java.util.Date()));
    }

    @Override
    public List<BookDTO> selectBookList() {
        return new ArrayList<>(bookList.values());
    }

    @Override
    public BookDTO selectOneBook(int sequence) {
        return bookList.get(sequence);
    }
}
