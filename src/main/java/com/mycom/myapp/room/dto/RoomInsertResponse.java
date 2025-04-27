package com.mycom.myapp.room.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoomInsertResponse {
    private int roomId;
    private String roomName;
    private String message;
}
