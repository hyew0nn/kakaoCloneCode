package com.mycom.myapp.room.dao;

import com.mycom.myapp.room.dto.RoomDto;
import com.mycom.myapp.room.dto.RoomMemberDto;
import com.mycom.myapp.room.dto.RoomParamDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomDao {
    List<Map<String, Object>> listRooms(int userId);
    int listRoomsCount(int userId);

    List<RoomDto> searchListRooms(RoomParamDto roomParam);

    int createRoom(RoomDto roomDto);
    int insertRoomMember(RoomMemberDto roomMemberDto);

    RoomDto detailRoom(RoomParamDto roomParam);
}
