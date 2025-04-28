package com.mycom.myapp.user.service;

import com.mycom.myapp.config.exception.BusinessException;
import com.mycom.myapp.config.exception.UserExceptions;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.mapper.UserMapper;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.dto.UserSignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserSignUpResponse insertUser(UserDto userDto) {
        UserSignUpResponse userSignUpResponse = new UserSignUpResponse();

        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .build();

        int insertResult = userMapper.insertUser(user);

        if (insertResult <= 0) {
            throw new UserExceptions.UserCreationException();
        }

        userSignUpResponse.setMessage("insert user success");
        userSignUpResponse.setUserId(user.getId());

        return userSignUpResponse;
    }

}
