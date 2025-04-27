package com.mycom.myapp.room.service;

import com.mycom.myapp.room.dto.*;

public interface RoomService {
    RoomInsertResponse insertRoom(RoomInsertRequest roomInsertRequest);
    RoomsListResponse listRooms(int userId);
    RoomDetailResponse detailRoom(int roomId);
}
