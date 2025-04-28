package com.mycom.myapp.auth.service;

import com.mycom.myapp.auth.dto.UserLoginRequest;
import com.mycom.myapp.auth.dto.UserLoginResponse;
import com.mycom.myapp.config.exception.UserExceptions;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        User user = userMapper.findByEmail(userLoginRequest.getEmail());

        if(user == null) {
            throw  new UserExceptions.UserNotFoundException(userLoginRequest.getEmail());
        }

        if (passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            UserDto userDto = UserDto.builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .build();

            userLoginResponse.setUserId(user.getId());
            userLoginResponse.setUserDto(userDto);
            userLoginResponse.setMessage("success");
        } else {
            userLoginResponse.setMessage("email or password fail");
        }

        return userLoginResponse;
    }
}
