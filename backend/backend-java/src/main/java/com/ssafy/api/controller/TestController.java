package com.ssafy.api.controller;

import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.request.ValidateEmailReq;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/hello")
    public String login() {

        return "hello";
    }

    @PostMapping("/hello/post")
    public String testPost(@RequestBody ValidateEmailReq validateEmailReq) {

        System.out.println(validateEmailReq.getEmail());
        System.out.println(validateEmailReq.getEmail());
        System.out.println(validateEmailReq.getEmail());

        return "hello post";
    }
}
