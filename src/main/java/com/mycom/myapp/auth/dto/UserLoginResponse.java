package com.mycom.myapp.auth.dto;

import com.mycom.myapp.user.dto.UserDto;
import lombok.Data;

@Data
public class UserLoginResponse {
    private String message;
    private int userId;
    private UserDto userDto;
}
