package com.mycom.myapp.auth.service;

import com.mycom.myapp.auth.dto.UserLoginRequest;
import com.mycom.myapp.auth.dto.UserLoginResponse;

public interface AuthService {
    UserLoginResponse login(UserLoginRequest userLoginRequest);
}
