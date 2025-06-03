package com.javaconfig;

import com.common.Account;
import com.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        MemberDTO member = context.getBean(MemberDTO.class);

        System.out.println("member = " + member);

        Account account = member.getPersonalAccount();

        System.out.println(account.getBalance());
        System.out.println(account.deposit(10000));
        System.out.println(account.getBalance());
        System.out.println(account.withDraw(4000));
        System.out.println(account.getBalance());
    }
}
