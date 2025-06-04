package com.section01.autowired.subsection02.constructor;

import com.section01.common.BookDAO;
import com.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookServiceConstructor")
public class BookService {
    private final BookDAO bookDAO;

    // 생성자 쪽에 @Autowired를 붙인 생성자 주입이다.
    // 생성자가 두 개 이상일 때는 반드시 어노테이션을 붙여야 하지만 하나일 경우에는 생략해도 상관 없다.
    @Autowired
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<BookDTO> selectAllBooks() {
        return bookDAO.selectBookList();
    }

    public BookDTO searchBookBySequence(int sequence) {
        return bookDAO.selectOneBook(sequence);
    }
}


/*
    Spring 4.3 버전 이후로는 생성자가 한 개뿐이라면 @Autowired 어노테이션을 생략해도 자동으로 생성자 주입이 동작한다.
    단, 생성자가 2개 이상일 경우에는 명시적으로 작성을 해주어야 한다.
*/

/*
    [생성자 주입의 장점]
    객체가 생성될 때 모든 의존성이 주입되므로 의존성을 보장할 수 있다.
    - 순환 참조에 대한 필드 주입/세터 주입은 메소드 실행 시점에 오류가 발생한다.
    - 생성자 주입은 어플리케이션 실행 시점에 오류가 발생한다.

    객체의 불변성을 보장할 수 있다.
    - 필드에 final 사용 가능하고 객체 생성 이후 의존성을 변경할 수 없어 안정성이 보장된다.

    코드 가독성이 좋다.
    - 해당 객체가 어떤 의존성을 가지고 있는지 명확히 알 수 있다.

*/