package com.mycom.myapp.room.service;

import com.mycom.myapp.room.dto.RoomDto;
import com.mycom.myapp.room.dto.RoomMemberDto;
import com.mycom.myapp.room.dto.RoomParamDto;
import com.mycom.myapp.room.dto.RoomResponseDto;

public interface RoomService {
    RoomResponseDto listRooms(int userId);
//    RoomResponseDto searchListRoom(String searchWord);
//
    RoomResponseDto detailRoom(RoomParamDto roomParam);
    RoomResponseDto createRoom(RoomParamDto roomParam);
}
