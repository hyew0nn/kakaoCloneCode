package com.mycom.myapp.room.service;

import com.mycom.myapp.room.dto.*;
import com.mycom.myapp.room.mapper.RoomMapper;
import com.mycom.myapp.room.entity.Room;
import com.mycom.myapp.room.entity.RoomMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {
    private final RoomMapper roomMapper;

    @Override
    public RoomInsertResponse insertRoom(RoomInsertRequest roomInsertRequest) {
        RoomInsertResponse roomInsertResponse = new RoomInsertResponse();

        Room roomDto = new Room();
        roomDto.setName(roomInsertRequest.getRoomName());

        int roomRet = roomMapper.insertRoom(roomDto);

        RoomMember roomMember = new RoomMember();
        roomMember.setRoomId(roomDto.getId());
        roomMember.setUserId(roomInsertRequest.getUserId());

        int memberRet = roomMapper.insertRoomMember(roomMember);

        if(roomRet > 0 && memberRet >0) {
            roomInsertResponse.setMessage("create room success");
            roomInsertResponse.setRoomId(roomDto.getId());
            roomInsertResponse.setRoomName(roomDto.getName());
        } else roomInsertResponse.setMessage("create room fail");

        return roomInsertResponse;
    }

    @Override
    public RoomsListResponse listRooms(int userId) {
        RoomsListResponse roomsListResponse = new RoomsListResponse();

        List<Room> rooms = roomMapper.listRooms(userId);

        List<RoomDto> roomList = new ArrayList<>();

        rooms.forEach(room -> {
            RoomDto roomDto = RoomDto.builder()
                    .roomId(room.getId())
                    .roomName(room.getName())
                    .build();
            roomList.add(roomDto);
        });

        roomsListResponse.setRooms(roomList);
        roomsListResponse.setRoomsCount(roomList.size());
        roomsListResponse.setMessage("call list rooms success");

        return roomsListResponse;
    }

    @Override
    public RoomDetailResponse detailRoom(int roomId) {
        RoomDetailResponse roomDetailResponse = new RoomDetailResponse();

        Room roomDto = roomMapper.detailRoom(roomId);
        roomDetailResponse.setRoomId(roomDto.getId());
        roomDetailResponse.setRoomName(roomDto.getName());

        List<RoomMemberDto> roomMember = roomMapper.detailRoomMembers(roomId);;

        roomDetailResponse.setMembers(roomMember);
        roomDetailResponse.setMemberCount(roomMember.size());
        roomDetailResponse.setMessage("call detail room success");

        return roomDetailResponse;
    }


}
