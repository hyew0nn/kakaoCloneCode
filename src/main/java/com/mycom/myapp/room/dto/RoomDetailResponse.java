package com.mycom.myapp.room.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomDetailResponse {
    private String message;
    private int roomId;
    private String roomName;
    private List<RoomMemberDto> members;
    private int memberCount;
}
