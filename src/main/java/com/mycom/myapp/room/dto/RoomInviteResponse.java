package com.mycom.myapp.room.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomInviteResponse {
    private String message;
    private int roomId;
    private String inviteeName;
}
