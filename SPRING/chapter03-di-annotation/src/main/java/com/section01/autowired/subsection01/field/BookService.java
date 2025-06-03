package com.section01.autowired.subsection01.field;

import com.section01.common.BookDAO;
import com.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* @Service : @Component의 세분화 어노테이션의 한 종류로 Service 계층에서 사용한다. */
@Service("bookServiceField")
public class BookService {

    /* BookDAO 타입의 빈 객체를 이 프로퍼티에 자동으로 주입해준다. */
    // field 주입을 하면 final을 사용하지 못한다.
    // field가 많아지면 단일 책임의 원칙에 어긋난다.
    // 따라서, field 주입보다는 생성자 주입을 권장한다.
    @Autowired
     private BookDAO bookDAO;

    public List<BookDTO> selectAllBooks() {
        return bookDAO.selectBookList();
    }

    public BookDTO searchBookBySequence(int sequence) {
        return bookDAO.selectOneBook(sequence);
    }
}
