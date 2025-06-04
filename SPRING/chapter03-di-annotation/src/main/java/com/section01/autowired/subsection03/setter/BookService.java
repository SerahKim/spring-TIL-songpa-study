package com.section01.autowired.subsection03.setter;

import com.section01.common.BookDAO;
import com.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookServiceSetter")
public class BookService {
    private BookDAO bookDAO;

    /* BookDAO 타입의 빈 객체를 setter에 자동으로 주입해준다. */
    // setter도 final을 사용할 수 없어 불변성을 보장할 수 없다.
    // 기본 생성자를 통해 생성되고 setter함수를 호출하여 BookDAO를 초기화하고 있다.
    @Autowired
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<BookDTO> selectAllBooks() {
        return bookDAO.selectBookList();
    }

    public BookDTO searchBookBySequence(int sequence) {
        return bookDAO.selectOneBook(sequence);
    }

}
