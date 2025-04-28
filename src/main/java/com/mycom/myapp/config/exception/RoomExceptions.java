package com.mycom.myapp.config.exception;

import com.mycom.myapp.config.exception.BusinessException;

public class RoomExceptions {

    public static class RoomNotFoundException extends BusinessException {
        public RoomNotFoundException(int roomId) {
            super("ROOM_NOT_FOUND", String.format("ID가 %d인 방을 찾을 수 없습니다.", roomId));
        }
    }

    public static class RoomCreationException extends BusinessException {
        public RoomCreationException() {
            super("ROOM_CREATION_FAILED", "방 생성에 실패했습니다.");
        }
    }

    public static class RoomMemberAddException extends BusinessException {
        public RoomMemberAddException() {
            super("MEMBER_ADD_FAILED", "방에 회원 추가에 실패했습니다.");
        }
    }
}