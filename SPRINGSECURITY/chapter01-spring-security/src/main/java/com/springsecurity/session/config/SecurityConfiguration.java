package com.springsecurity.session.config;

import com.springsecurity.session.common.UserRole;
import com.springsecurity.session.config.handler.AuthFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private AuthFailHandler authFailHandler;

    // 비밀 번호 암호화를 위한 Spring Security에서 제공하는 interface
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 암호화를 위해 가장 많이 쓰는 알고리즘
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적인 데이터에는 무시
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        /* 요청에 대한 권한 체크 */
        http.authorizeHttpRequests( auth -> {
            auth.requestMatchers("/auth/login", "/user/signup", "/auth/fail", "/", "/main").permitAll(); // 권한이 없어도 접근 가능
            auth.requestMatchers("/admin/*").hasAnyAuthority(UserRole.ADMIN.getRole()); // "/admin/*" 접근하기 위해서는 UserRole.ADMIN.getRole() 권한을 가지고 있어야함.
            auth.requestMatchers("/user/*").hasAnyAuthority(UserRole.USER.getRole());
            auth.anyRequest().authenticated(); // 다른 요청에 대해서는 인증된 사용자만 허용 (permitAll과 반대)

        }).formLogin( login -> {
            login.loginPage("/auth/login");
            login.usernameParameter("user"); // login.html의 ID의 name과 일치
            login.passwordParameter("pwd"); // login.html의 PassWord의 name과 일치
            login.defaultSuccessUrl("/", true);
            login.failureHandler(authFailHandler);

        }).logout( logout -> {
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout")); // "/auth/logout"요청은 로그아웃 요청이라는 뜻
            logout.deleteCookies("JSESSIONID");
            logout.invalidateHttpSession(true);
            logout.logoutSuccessUrl("/");

        }).sessionManagement( session -> {
            session.maximumSessions(1);
            session.invalidSessionUrl("/");

        }).csrf( csrf -> csrf.disable());
        // csrf : 내가 보낸 것처럼 악의적으로 요청을 보내는 것
        // 개발을 진행하는 동안 csrf 설정으로 인해 안되는 경우가 있어 개발하는 동아만 .disable()

        return http.build();
    }
}
