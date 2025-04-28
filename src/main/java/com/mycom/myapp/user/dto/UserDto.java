package com.mycom.myapp.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private int id;
    private String email;
    private String password;
    private String name;
}
