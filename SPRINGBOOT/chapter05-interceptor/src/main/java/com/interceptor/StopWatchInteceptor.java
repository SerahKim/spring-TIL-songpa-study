package com.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;

// Configuration 쪽에 등록을 해줘야 사용이 가능하다.
@Component
public class StopWatchInteceptor implements HandlerInterceptor {

    private final MenuService menuService;

    public StopWatchInteceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    /* 핸들러 메소드를 호출하기 이전에 동작하는 메소드(전처리 메소드) */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandler 호출함...");

        long startTime = System.currentTimeMillis();

        // 오버라이드 받은 메소드이기 때문에 container가 관리해주고 있다.
        // 따라서 직접적으로 메소드를 호출하지 못하기 때문에 HttpServletRequest 객체에 담아 전달해야 한다.
        request.setAttribute("startTime", startTime);

        // false이면 handler가 동작하지 않는다.
        return true;
    }

    /* 핸들러 메소드 동작 이후에 수행되는 메소드(후처리) */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler 호출함...");

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();

        // startTime 값은 다 썻기 때문에 삭제
        request.removeAttribute("startTime");

        modelAndView.addObject("interval", endTime - startTime);
    }

    /* 요청을 했을 때 성공 실패 관계없이 무조건 동작하는 메소드 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion 호출함...");

        menuService.method();
    }

}
