package com.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie")
public class CookieHandlerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);

        /*
        쿠키를 사용하는 방법은 간단하며, 쿠키를 사용하는 절차는 다음과 같다.
        1. 쿠키를 생성한다.
        2. 생성한 쿠키의 만료 시간을 설정한다.(적절하게, 보안에 취약함)
        3. 응답 헤더에 쿠키를 담는다.
        4. 응답을 보낸다.

        하지만 쿠키는 일부 제약사항이 있다.
        쿠키의 이름은 ascii문자만을 사용해야하며 한 번 설정한 쿠키의 이름은 변경할 수 없다.
        또한 쿠키의 이름에는 공백문자와 일부 특수문자([ ] ( ) = , " \ ? @ : ;)는 사용할 수 없다.
        */

        /* 값을 공유할 수 없다. */
//        request.setAttribute("firstName", firstName);
//        request.setAttribute("lastName", lastName);

        Cookie firstNameCookie = new Cookie("firstName", firstName);
        Cookie lastNameCookie = new Cookie("lastName", lastName);

        firstNameCookie.setMaxAge(60 * 60 * 24); // 만료시간(초단위) => 하루
        lastNameCookie.setMaxAge(60 * 60 * 24);

        response.addCookie(firstNameCookie);
        response.addCookie(lastNameCookie);

        response.sendRedirect("redirect");
    }
}
