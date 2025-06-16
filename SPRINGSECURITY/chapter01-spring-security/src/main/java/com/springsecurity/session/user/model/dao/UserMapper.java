package com.springsecurity.session.user.model.dao;

import com.springsecurity.session.user.model.dto.LoginUserDTO;
import com.springsecurity.session.user.model.dto.SignupDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper // xml파일(DB)와 자바를 연결해주는 어노테이션(DAO 역할)
public interface UserMapper {
    int regist(SignupDTO signupDTO);

    LoginUserDTO findByUserName(String userName);
}
