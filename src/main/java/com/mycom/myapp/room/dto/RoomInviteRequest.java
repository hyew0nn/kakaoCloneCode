package com.mycom.myapp.room.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoomInviteRequest {
    private int inviterId;
    private int inviteeId;
}
