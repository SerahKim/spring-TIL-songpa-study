package com.xmlconfig;

import com.common.Account;
import com.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context
                = new GenericXmlApplicationContext("xmlconfig/spring-context.xml");

        MemberDTO member = context.getBean(MemberDTO.class);
        System.out.println("member = " + member);

        Account account = member.getPersonalAccount();
        System.out.println(account.getBalance());
        System.out.println(account.deposit(10000));
        System.out.println(account.getBalance());
        System.out.println(account.withDraw(3000));
        System.out.println(account.getBalance());
    }
}
