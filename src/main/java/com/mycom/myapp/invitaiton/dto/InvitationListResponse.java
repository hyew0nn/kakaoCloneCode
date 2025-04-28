package com.mycom.myapp.invitaiton.dto;

import com.mycom.myapp.invitaiton.mapper.InvitationMapper;
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
