package com.headers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

@WebServlet("/headers")
public class ResponseHeaderPrintServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // 헤더 설정
//        response.setHeader("Refresh", "1");

        PrintWriter out = response.getWriter();

        long currentTime = System.currentTimeMillis();
        out.print("<h1>" + currentTime + "</h1>");

        out.close();

        Collection<String > responseHeaders = response.getHeaderNames();
        Iterator<String> iter = responseHeaders.iterator();
        while (iter.hasNext()) {
            String headerName = iter.next();
            System.out.println(headerName + " : " + response.getHeader(headerName));
        }

    }
}
