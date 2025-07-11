package com.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("redirect servlet 요청 확인");

        /* 값을 공유할 수 없다. */
//        String firstNAme = (String) request.getAttribute("firstNAme");
//        String lastName = (String) request.getAttribute("lastName");
//
//
//        System.out.println("firstNAme = " + firstNAme); // null
//        System.out.println("lastName = " + lastName); // null

        /*
        쿠키를 사용하는 방법도 간단하다.
        1. request에서 쿠기 목록을 쿠키 배열 형태로 꺼내온다.
        2. 쿠키의 getName과 getValue를 이용해 쿠키에 담긴 값을 사용한다.
        */

        Cookie[] cookies = request.getCookies();
        
        for (Cookie cookie : cookies) {
            System.out.println("cookie = " + cookie);
            System.out.println("key = " + cookie.getName());
            System.out.println("value = " + cookie.getValue());
        }
    }

    /*
    쿠키는 텍스트 파일 형태로 클라이언트 컴퓨터에 저장된다.
    그렇기 떄문에 개인 PC는 크게 상관 없지만 공용 PC 등 다른 사용자와 함께 쓰는 컴퓨터이면 (민감한 개인정보를 포함한 경우) 보안에 취약하다.
    따라서 민감한 개인 정보를 취급하는 경우에는 쿠키보다는 세션을 이용한다.
    세션은 쿠키와 유사한 형태로 key=value 쌍으로 저장되지만 서버(톰캣)에서 관리되므로 보안에 더 우수하다는 장점을 가진다.
    */
}
