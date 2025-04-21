package com.mycom.myapp.room.service;

import com.mycom.myapp.room.dao.RoomDao;
import com.mycom.myapp.room.dto.RoomDto;
import com.mycom.myapp.room.dto.RoomMemberDto;
import com.mycom.myapp.room.dto.RoomParamDto;
import com.mycom.myapp.room.dto.RoomResponseDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomDao roomDao;

    RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public RoomResponseDto listRooms(int userId) {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        try {
            List<Map<String, Object>> resultList = roomDao.listRooms(userId);
            Map<Integer, RoomDto> roomMap = new LinkedHashMap<>();

            for (Map<String, Object> row : resultList) {
                int roomId = (int) row.get("room_id");

                RoomDto room = roomMap.get(roomId);
                if (room == null) {
                    room = new RoomDto();
                    room.setId(roomId);
                    room.setName((String) row.get("room_name"));
                    room.setCreatedAt(((Timestamp) row.get("room_created_at")).toLocalDateTime());

                    Timestamp updatedAt = (Timestamp) row.get("room_updated_at");
                    room.setUpdatedAt(updatedAt != null ? updatedAt.toLocalDateTime() : null);

                    room.setMembers(new ArrayList<>());
                    roomMap.put(roomId, room);
                }

                RoomMemberDto member = new RoomMemberDto();
                member.setId((int) row.get("member_id"));
                member.setRoomId(roomId);
                member.setUserId((int) row.get("member_user_id"));
                member.setRole((String) row.get("member_role"));
                member.setJoinedAt(((Timestamp) row.get("member_joined_at")).toLocalDateTime());

                room.getMembers().add(member);
            }

            for (RoomDto room : roomMap.values()) {
                room.setMemberCount(room.getMembers().size());
            }

            // 여기서 RoomResponseDto에 담기
            roomResponseDto.setRooms(new ArrayList<>(roomMap.values()));
            roomResponseDto.setCount(roomMap.size());

//            int count = roomDao.listRoomsCount(userId);
//            roomResponseDto.setCount(count);

            roomResponseDto.setResponse("success");

        }catch (Exception e) {
            roomResponseDto.setResponse("fail");;
            e.printStackTrace();
        }
        return roomResponseDto;
    }

    @Override
    public RoomResponseDto detailRoom(RoomParamDto roomParam) {
        RoomResponseDto roomResponseDto = new RoomResponseDto();

        try {
            RoomDto room = roomDao.detailRoom(roomParam);

            roomResponseDto.setRoom(room);
            roomResponseDto.setResponse("success");

        }catch (Exception e) {
            roomResponseDto.setResponse("fail");
            e.printStackTrace();
        }
        return roomResponseDto;
    }

    @Override
    public RoomResponseDto createRoom(RoomParamDto roomParam) {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        try {
            RoomDto roomDto = new RoomDto();
            roomDto.setName(roomParam.getName());

            int roomRet = roomDao.createRoom(roomDto);

            RoomMemberDto roomMemberDto = new RoomMemberDto();
            roomMemberDto.setRoomId(roomDto.getId());
            roomMemberDto.setUserId(roomParam.getUserId());
            roomMemberDto.setRole(roomParam.getUserRole());

            int memberRet = roomDao.insertRoomMember(roomMemberDto);

            if(roomRet > 0 && memberRet >0) {
                roomResponseDto.setResponse("success");
            } else roomResponseDto.setResponse("fail");

        } catch (Exception e) {
            roomResponseDto.setResponse("fail");
            e.printStackTrace();
        }
        return roomResponseDto;
    }

}
