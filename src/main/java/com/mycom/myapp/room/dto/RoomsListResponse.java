package com.mycom.myapp.room.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoomsListResponse {
    private String message;
    private List<RoomDto> rooms;
    private int roomsCount;
}
