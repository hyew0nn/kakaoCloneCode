package com.mycom.myapp.room.service;

import com.mycom.myapp.config.exception.InvitationExceptions;
import com.mycom.myapp.config.exception.RoomExceptions;
import com.mycom.myapp.config.exception.UserExceptions;
import com.mycom.myapp.invitaiton.entity.Invitation;
import com.mycom.myapp.invitaiton.entity.InvitationType;
import com.mycom.myapp.invitaiton.mapper.InvitationMapper;
import com.mycom.myapp.room.dto.*;
import com.mycom.myapp.room.mapper.RoomMapper;
import com.mycom.myapp.room.entity.Room;
import com.mycom.myapp.room.entity.RoomMember;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.mapper.UserMapper;
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
    private final InvitationMapper invitationMapper;
    private final UserMapper userMapper;

    @Override
    public RoomInsertResponse createRoom(RoomInsertRequest roomInsertRequest) {
        RoomInsertResponse roomInsertResponse = new RoomInsertResponse();

        // 유효성 검사
        if (roomInsertRequest.getRoomName() == null || roomInsertRequest.getRoomName().trim().isEmpty()) {
            throw new IllegalArgumentException("방 이름은 비어있을 수 없습니다.");
        }

        Room roomDto = new Room();
        roomDto.setName(roomInsertRequest.getRoomName());

        int roomInsertResult = roomMapper.insertRoom(roomDto);
        if (roomInsertResult <= 0) {
            throw new RoomExceptions.RoomCreationException();
        }

        RoomMember roomMember = new RoomMember();
        roomMember.setRoomId(roomDto.getId());
        roomMember.setUserId(roomInsertRequest.getUserId());
        roomMember.setRole("방장");

        int insertMemberResult = roomMapper.insertRoomMember(roomMember);
        if (insertMemberResult <= 0) {
            throw new RoomExceptions.RoomMemberAddException();
        }

        roomInsertResponse.setMessage("create room success");
        roomInsertResponse.setRoomId(roomDto.getId());
        roomInsertResponse.setRoomName(roomDto.getName());

        return roomInsertResponse;
    }

    @Override
    public RoomListResponse getRoomsByUserId(int userId) {
        RoomListResponse roomListResponse = new RoomListResponse();

        List<Room> rooms = roomMapper.findRoomsByUserId(userId);

        List<RoomDto> roomList = new ArrayList<>();

        rooms.forEach(room -> {
            RoomDto roomDto = RoomDto.builder()
                    .roomId(room.getId())
                    .roomName(room.getName())
                    .build();
            roomList.add(roomDto);
        });

        roomListResponse.setRooms(roomList);
        roomListResponse.setRoomsCount(roomList.size());
        roomListResponse.setMessage("call list rooms success");

        return roomListResponse;
    }

    @Override
    public RoomDetailResponse getRoomDetails(int roomId) {
        RoomDetailResponse roomDetailResponse = new RoomDetailResponse();

        Room roomDto = roomMapper.findRoomById(roomId);

        if (roomDto == null) {
            throw new RoomExceptions.RoomNotFoundException(roomId);
        }

        roomDetailResponse.setRoomId(roomDto.getId());
        roomDetailResponse.setRoomName(roomDto.getName());

        List<RoomMemberDto> roomMember = roomMapper.findRoomMembersByRoomId(roomId);;

        roomDetailResponse.setMembers(roomMember);
        roomDetailResponse.setMemberCount(roomMember.size());
        roomDetailResponse.setMessage("call detail room success");

        return roomDetailResponse;
    }

    @Override
    public RoomInviteResponse inviteToRoom(RoomInviteRequest roomInviteRequest, int roomId) {
        RoomInviteResponse roomInviteResponse = new RoomInviteResponse();

        Room room = roomMapper.findRoomById(roomId);
        if (room == null) {
            throw new RoomExceptions.RoomNotFoundException(roomId);
        }

        User inviter = userMapper.findById(roomInviteRequest.getInviterId());

        if(inviter == null) {
            throw new UserExceptions.UserNotFoundException(roomInviteRequest.getInviterId());
        }

        User invitee = userMapper.findById(roomInviteRequest.getInviteeId());

        if(invitee == null) {
            throw new UserExceptions.UserNotFoundException(roomInviteRequest.getInviteeId());
        }

        Invitation invitation = Invitation.builder()
                .chatroomId(roomId)
                .inviterId(roomInviteRequest.getInviterId())
                .inviteeId(roomInviteRequest.getInviteeId())
                .invitationType(InvitationType.pending)
                .build();

        int invitationInsertResult = invitationMapper.insertInvitation(invitation);

        if (invitationInsertResult <= 0) {
            throw new InvitationExceptions.InvitationCreationException();
        }

        roomInviteResponse.setMessage("insert invitation success");
        roomInviteResponse.setRoomId(invitation.getChatroomId());
        roomInviteResponse.setInviteeName(inviter.getName());

        return roomInviteResponse;
    }
}
