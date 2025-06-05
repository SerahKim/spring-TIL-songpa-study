package com.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.aop");

        MemberService memberService = context.getBean("memberService", MemberService.class);

        System.out.println("========전체 멤버 조회==========");
        System.out.println(memberService.selectMembers());
        System.out.println("========아이디로 멤버 조회==========");
        System.out.println(memberService.selectMember(1L));
    }
}
