package com.mycom.myapp.invitation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InvitationDto {
    private String roomName;
    private String inviterName;
    private int inviteId;
    private String status;
    private LocalDateTime invitedAt;
}
