package com.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

// /first 뒤에 오는 것이 무엇이든 다 실행한다.
@WebFilter("/first/*")
public class FirstFilter implements Filter {

    public FirstFilter() {
        System.out.println("FirstFilter 인스턴스 생성!");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /* 필터 인스턴스가 최초 생성될 때 호출되는 메소드 */
        System.out.println("Filter init 호출");
    }

    @Override
    public void destroy() {
        /* 필터 인스턴스가 소멸될 때 호출되는 메소드(=톰캣 종료 시) */
        System.out.println("Filter destroy 호출");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /* servlet으로 request가 전달되기 전에 요청을 가로채는 역할을 하는 메소드 */
        System.out.println("filter doFilter() 호출");

        /* 필터에서 처리할 코드를 작성한다.*/

        /* 처리를 한 뒤 다음 필터 혹은 서블릿의 doGet/doPost를 호출한다. */
        filterChain.doFilter(servletRequest, servletResponse);

        /* 서블릿에서 처리 후에 다시 수행할 내용이 있으면 작성한다.*/
        System.out.println("servlet 요청 수행 완료!");

    }

}
