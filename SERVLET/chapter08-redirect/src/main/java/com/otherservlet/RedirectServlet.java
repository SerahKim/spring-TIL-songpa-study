package com.otherservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("이 서블릿으로 redirect 성공?");

        StringBuilder redirectText = new StringBuilder();
        redirectText.append("<!doctype html>\n")
                .append("<head>\n")
                .append("</head>\n")
                .append("<body>\n")
                .append("<h1>이 서블릿으로 redirect 성공!</h1>")
                .append("</body>\n")
                .append("</html>");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print(redirectText);

        out.close();

        /*
        redirect하면 url이 재작성되어 새로고침할 때 redirect된 페이지에 대한 요청을 반복한다.
        즉, 이전 요청에 포함된 정보는 남아있지 않고 url이 변경되는 것이 redirect 특징이다.
        http 요청은 요청 시 잠시 connection을 맥조 응답 시에도 잠시 connection을 맺으려
        요청 단위 당 request 개체는 한 개만 생성된다.
        따라서 첫 요청 시의 request와 현재 redirect된 페이지의 request는 서로 다른 객체이므로,
        redirect를쓰면 이전 서블릿의 값을 공유해서 사용할 수 없는 것이다.
        */
    }
}
