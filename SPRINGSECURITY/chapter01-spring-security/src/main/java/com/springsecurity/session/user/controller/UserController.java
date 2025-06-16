package com.springsecurity.session.user.controller;

import com.springsecurity.session.user.model.dto.SignupDTO;
import com.springsecurity.session.user.model.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 반환형 void : 반환형이 없는 경우 자신이 매핑되어 있는 주소를 가지고 있는 .html을 반환하게 된다.
    // resources/templates/user/signup을 반환
    @GetMapping("/signup")
    public void signup() {}

    @PostMapping("/signup")
    public ModelAndView signup(ModelAndView mv, @ModelAttribute SignupDTO signupDTO) {

        int result = userService.regist(signupDTO);

        String message = "";

        if (result > 0) {
            message = "회원가입이 정상적으로 완료되었습니다.";
            mv.setViewName("auth/login");
        } else {
            message = "회원 가입에 실패하였습니다.";
            mv.setViewName("user/signup");
        }

        mv.addObject("message", message);

        return mv;
    }
}
