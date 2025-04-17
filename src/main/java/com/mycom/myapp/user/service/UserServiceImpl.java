package com.mycom.myapp.user.service;

import com.mycom.myapp.user.dao.UserDao;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResponseDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto insertUser(UserDto userDto) {
        UserResponseDto userResponseDto = new UserResponseDto();
        try {
            // 👉 비밀번호 암호화
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encodedPassword); // 암호화된 비밀번호로 덮어쓰기

            int ret = userDao.insertUser(userDto);
            if (ret > 0) userResponseDto.setResponse("success");
            else userResponseDto.setResponse("fail");
        }catch (Exception e){
            userResponseDto.setResponse("fail");
            e.printStackTrace();
        }
        return userResponseDto;
    }
}
