package com.mycom.myapp.invitation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class InvitationListResponse {
    private String message;
    private int userId;
    private List<InvitationDto> invitations;
}
