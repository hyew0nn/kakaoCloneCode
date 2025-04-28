package com.mycom.myapp.invitaiton.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvitationRejectResponse {
    private String message;
    private int roomId;
    private String roomName;
}
