package com.restapi.section02.responseentity;

import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entity")
public class ResponseEntityTestController {
    /*
    [ResponseEntity]
    결과 데이터와 HTTP 상태 코드를 직접 제어할 수 있는 클래스이다.
    HttpStatus, HttpHeaders, HttpBody를 포함한다.
    */

    private List<UserDTO> users;

    public ResponseEntityTestController() {
        users = new ArrayList<>();
        users.add(new UserDTO(1, "user01", "pass01", "다람쥐", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "코알라", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "판다", new Date()));
    }

    /* 회원 전체 조회 */
    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {

        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    /* 회원 상세 조회 */
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo) {

        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo)
                .collect(Collectors.toList()).get(0);

        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", foundUser);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ResponseMessage(200, "조회 성공", responseMap));
    }

    /* 신규 회원 등록 */
    @PostMapping("/users")
    public ResponseEntity<?> registUser(@RequestBody UserDTO newUser) {
        System.out.println("newUser = " + newUser);

        int lastUserNo = users.get(users.size() - 1).getNo();
        newUser.setNo(lastUserNo + 1);

        users.add(newUser);

        return ResponseEntity
                .created(URI.create("/entity/users/" + lastUserNo))
                .build();
    }

    /* 기존 회원 수정 */
    @PutMapping("/users/{userNo}")
    public ResponseEntity<?> modifyUser(@PathVariable int userNo, @RequestBody UserDTO modifyInfo) {
           UserDTO founder = users.stream().filter(user -> user.getNo() == userNo)
                   .collect(Collectors.toList()).get(0);

           founder.setId(modifyInfo.getId());
           founder.setPwd(modifyInfo.getPwd());
           founder.setName(modifyInfo.getName());

           return ResponseEntity
                   .created(URI.create("/entity/users/" + userNo))
                   .build();
    }

    /* 기존 회원 삭제 */
    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> removeUser(@PathVariable int userNo) {
        UserDTO foundUser = users.stream()
                .filter(user -> user.getNo() == userNo)
                .collect(Collectors.toList()).get(0);


        users.remove(foundUser);

        return ResponseEntity
                .noContent()
                .build();
    }
}
