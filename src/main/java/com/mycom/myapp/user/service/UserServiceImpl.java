package com.mycom.myapp.user.service;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.mapper.UserMapper;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.dto.UserSignUpResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserSignUpResponse insertUser(UserDto userDto) {
        UserSignUpResponse userSignUpResponse = new UserSignUpResponse();

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        User user = convertToEntity(userDto);
        int ret = userMapper.insertUser(user);

        if (ret > 0) {
            userSignUpResponse.setMessage("success");
            userSignUpResponse.setUserId(user.getId());
        }
        else userSignUpResponse.setMessage("fail");

        return userSignUpResponse;
    }

    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());

        return user;
    }
}
