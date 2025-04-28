package com.mycom.myapp.config.exception;

public class UserExceptions {

    public static class UserNotFoundException extends BusinessException {
        public UserNotFoundException(int userId) {
            super("USER_NOT_FOUND", String.format("ID가 %d인 회원을 찾을 수 없습니다.", userId));
        }

        public UserNotFoundException(String email) {
            super("USER_NOT_FOUND", String.format("Email 가 %s인 회원을 찾을 수 없습니다.", email));
        }
    }

    public static class UserCreationException extends BusinessException {
        public UserCreationException() {
            super("USER_CREATION_FAILED", "회원 생성에 실패했습니다.");
        }
    }
}