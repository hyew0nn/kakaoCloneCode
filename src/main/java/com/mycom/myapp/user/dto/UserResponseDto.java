package com.mycom.myapp.user.dto;

// User 관련 다양한 작업을 수행하면서 Front 에 결과를 리턴하는 객체
public class UserResponseDto {
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
