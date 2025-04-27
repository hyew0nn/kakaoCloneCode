package com.mycom.myapp.user.mapper;

import com.mycom.myapp.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    int insertUser(User user);
    Optional<User> login(String email);
}
