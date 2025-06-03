package com.forward;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forward")
public class ReceiveInformationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        System.out.println("userId = " + userId);
        System.out.println("password = " + password);

        /*
        어떤 서블릿으로 위임할 것인지 대상 서블릿을 지정하는 인스턴스를 생성하고,
        forward() method를 통해 요청과 응답에 대한 정보를 전달하여 나머지 작업을 수행하도록 위임한다.
        이때 다른 서블릿으로 요청하기 위한 데이터는 request에 setAttribute로 전달하여
        받는 쪽에서는 getAtrribute를 이용해 꺼내서 사용한다.
        */
        request.setAttribute("userId", userId);

        RequestDispatcher rd = request.getRequestDispatcher("print");

        rd.forward(request, response);
    }
}
