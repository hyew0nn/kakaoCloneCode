package com.mycom.myapp.room.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class RoomInsertRequest {
    private String roomName;
    private int userId;
}
