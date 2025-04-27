package com.mycom.myapp.room.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDto {
    private int roomId;
    private String roomName;
//    private int memberCount;
}
