package com.mycom.myapp.room.mapper;

import com.mycom.myapp.room.dto.RoomMemberDto;
import com.mycom.myapp.room.entity.Room;
import com.mycom.myapp.room.entity.RoomMember;
import com.mycom.myapp.room.dto.RoomInsertRequest;
import com.mycom.myapp.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomMapper {
    int insertRoom(Room room);
    int insertRoomMember(RoomMember roomMember);

    List<Room> findRoomsByUserId(int userId);

    Room findRoomById(int roomId);
    List<RoomMemberDto> findRoomMembersByRoomId(int roomId);
}
