package com.mycom.myapp.user.service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserSignUpResponse;

public interface UserService {
    UserSignUpResponse insertUser(UserDto userDto);
}
