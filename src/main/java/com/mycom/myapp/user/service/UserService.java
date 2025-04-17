package com.mycom.myapp.user.service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResponseDto;

public interface UserService {
    UserResponseDto insertUser(UserDto userDto);
}
