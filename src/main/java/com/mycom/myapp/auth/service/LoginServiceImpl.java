package com.mycom.myapp.auth.service;

import com.mycom.myapp.auth.dao.LoginDao;
import com.mycom.myapp.user.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginDao loginDao;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginServiceImpl(LoginDao loginDao, BCryptPasswordEncoder passwordEncoder) {
        this.loginDao = loginDao;
        this.passwordEncoder = passwordEncoder;
    }

    // null 을 return 할 수 있는 메소드는 호출하는 쪽에 그것을 알리는 방법 Optional 을 사용.
    @Override
    public Optional<UserDto> login(UserDto userDto) {
        UserDto dto = loginDao.login(userDto.getEmail());
        if (dto != null && passwordEncoder.matches(userDto.getPassword(), dto.getPassword())) {
            return Optional.of(dto);
        }
        return Optional.empty();
    }
}
