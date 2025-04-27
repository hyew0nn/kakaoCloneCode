package com.mycom.myapp.auth.controller;

import com.mycom.myapp.auth.dto.UserLoginRequest;
import com.mycom.myapp.auth.dto.UserLoginResponse;
import com.mycom.myapp.auth.service.AuthService;
import com.mycom.myapp.user.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<UserLoginResponse> login(UserLoginRequest userLoginRequest, HttpSession session) {
        UserLoginResponse userLoginResponse = authService.login(userLoginRequest);
        UserDto userDto = userLoginResponse.getUserDto();

        if(userDto != null){
            session.setAttribute("userDto", userDto);
        }
        return ResponseEntity.ok(userLoginResponse);
    }

    @GetMapping("logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate(); // session을 제거하는 명령어.
        return ResponseEntity.ok("logout");
    }
}
