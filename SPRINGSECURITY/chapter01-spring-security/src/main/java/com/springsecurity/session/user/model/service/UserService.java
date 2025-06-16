package com.springsecurity.session.user.model.service;

import com.springsecurity.session.user.model.dao.UserMapper;
import com.springsecurity.session.user.model.dto.LoginUserDTO;
import com.springsecurity.session.user.model.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int regist(SignupDTO signupDTO) {

        // 기존 패스워드를 다시 암호화하여 singupDTO의 패스워드로 세팅
        signupDTO.setUserPass(passwordEncoder.encode(signupDTO.getUserPass()));

        int result = 0;

        try {
            result = userMapper.regist(signupDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public LoginUserDTO findByUserName(String userName) {
        LoginUserDTO login = userMapper.findByUserName(userName);

        if (!Objects.isNull(login)) {
            return login;
        } else {
            return null;
        }
    }
}
