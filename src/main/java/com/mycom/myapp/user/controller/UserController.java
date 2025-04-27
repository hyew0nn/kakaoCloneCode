package com.mycom.myapp.user.controller;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserSignUpResponse;
import com.mycom.myapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<UserSignUpResponse> insertUser(UserDto userdto) {
        UserSignUpResponse signupResponseUser = userService.insertUser(userdto);
        return ResponseEntity.ok(signupResponseUser);
    }
}
