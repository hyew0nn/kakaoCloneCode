package com.mycom.myapp.invitaiton.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class InvitationDto {
    private String roomName;
    private String inviterName;
    private int inviteId;
    private String status;
    private LocalDateTime invitedAt;
}
