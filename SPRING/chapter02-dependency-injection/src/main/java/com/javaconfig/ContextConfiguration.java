package com.javaconfig;

import com.common.Account;
import com.common.MemberDTO;
import com.common.PersonalAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {

    @Bean
    public Account accountGenerator() {
        return new PersonalAccount("110-234-56789", 1000);
    }

    @Bean
    public MemberDTO memberGenerator() {

        /* 자바 방식 */
        /* 1. 생성자 주입 */
//        return new MemberDTO(1, "홍길동", "010-1234-5678", "hong123@gmail.com", accountGenerator());

        /* 2. setter 주입 */
        MemberDTO member = new MemberDTO();
        member.setSequence(1);
        member.setName("유관순");
        member.setPhone("010-1212-2323");
        member.setEmail("you12@gmail.com");
        /* setter를 통해 Account를 생성하는 메소드를 호출한 리턴 값을 전달하여 bean을 조립할 수 있다. */
        member.setPersonalAccount(accountGenerator());

        return member;
    }
}
