package com.menu.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/menu/order")
public class MenuOrderServlet extends HttpServlet {

    /*뷰 응답은 Servlet보다 JSP가 더 잘한다 */
    /* 서블릿의 역할 3가지 */
    /* 1. 요청 받은 값 확인 및 검증 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String menuName = req.getParameter("menuName");
        int amount = Integer.parseInt(req.getParameter("amount"));

        System.out.println("menuName = " + menuName);
        System.out.println("amount = " + amount);

        /* 2. 비니지스 로직 처리 */
        int orderPrice = 0;

        switch (menuName) {
            case "햄버거":
                orderPrice = 6000 * amount;
                break;
            case "짜장면":
                orderPrice = 7000 * amount;
                break;
            case "짬뽕":
                orderPrice = 8000 * amount;
                break;
            case "순대국":
                orderPrice = 5000 * amount;
                break;
        }

        /* 3. 응답 페이지 생성 후 응답 */
        req.setAttribute("menuName", menuName);
        req.setAttribute("amount", amount);
        req.setAttribute("orderPrice", orderPrice);

        // 설정한 데이터를 보내줄 방향 정하기
        RequestDispatcher rd = req.getRequestDispatcher("/jsp/5_response.jsp");
        rd.forward(req, resp);
    }
}
