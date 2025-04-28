package com.mycom.myapp.room.service;

import com.mycom.myapp.room.dto.*;

public interface RoomService {
    RoomInsertResponse createRoom(RoomInsertRequest roomInsertRequest);
    RoomListResponse getRoomsByUserId(int userId);
    RoomDetailResponse getRoomDetails(int roomId);
    RoomInviteResponse inviteToRoom(RoomInviteRequest roomInviteRequest, int roomId);
}
