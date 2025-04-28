package com.mycom.myapp.room.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomListResponse {
    private String message;
    private List<RoomDto> rooms;
    private int roomsCount;
}
