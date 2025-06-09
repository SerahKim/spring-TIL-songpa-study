package com.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {
    @GetMapping("controller-null")
    public String nullPointerExceptionTest() {
        String str = null;
        System.out.println(str.charAt(0));

        return "/";
    }

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException exception) {
        System.out.println("controlelr 레벨의 exception 처리");

        return "error/nullPointer";
    }

    @GetMapping("controller-user")
    public String userExceptionTest() throws MemberRegistException {
        boolean check = true;

        if (check) {
            throw  new MemberRegistException("당신 같은 사람은 회원으로 받을 수 없습니다. 회원 등록 실패!");
        }
        return "/";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model, MemberRegistException exception) {
        System.out.println("contorller 레벨의 excpetion 처리");
        model.addAttribute("exception", exception);

        return "error/memberRegist";
    }
}
