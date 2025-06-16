package com.springsecurity.session.auth.model.service;

import com.springsecurity.session.auth.model.AuthDetails;
import com.springsecurity.session.user.model.dto.LoginUserDTO;
import com.springsecurity.session.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

// UserDetailsService : 전달 받은 id가 데이터베이스에 있는지 조회
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        LoginUserDTO login = userService.findByUserName(userName);

        if (Objects.isNull(login)) {
            throw new UsernameNotFoundException("해당하는 회원 정보가 존재하지 않습니다.");
        }

        return new AuthDetails(login);
    }
}
