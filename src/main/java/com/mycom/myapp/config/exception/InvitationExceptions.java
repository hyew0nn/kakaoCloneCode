package com.mycom.myapp.config.exception;

public class InvitationExceptions {

    public static class InvitationNotFoundException extends BusinessException {
        public InvitationNotFoundException(int invitationId) {
            super("INVITATION_NOT_FOUND", String.format("ID가 %d인 초대를 찾을 수 없습니다.", invitationId));
        }
    }

    public static class InvitationCreationException extends BusinessException {
        public InvitationCreationException() {
            super("INVITATION_CREATION_FAILED", "초대 생성에 실패했습니다.");
        }
    }

    public static class InvitationAlreadyProcessedException extends BusinessException {
        public InvitationAlreadyProcessedException(int invitationId) {
            super("INVITATION_ALREADY_PROCESSED",
                    String.format("초대(ID: %d)는 이미 처리되었습니다.", invitationId));
        }
    }
}