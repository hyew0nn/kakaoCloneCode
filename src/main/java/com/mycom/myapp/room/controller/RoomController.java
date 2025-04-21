package com.mycom.myapp.room.controller;

import com.mycom.myapp.room.dto.RoomDto;
import com.mycom.myapp.room.dto.RoomMemberDto;
import com.mycom.myapp.room.dto.RoomParamDto;
import com.mycom.myapp.room.dto.RoomResponseDto;
import com.mycom.myapp.room.service.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/rooms")
    public RoomResponseDto createRoom(RoomParamDto roomParamDto, HttpSession session) {
        // 이후 session 활용 -> userId 처리
//        int userId = ((UserDto)session.getAttribute("userDto")).getId(); // session 이 invalidate 된 상황 (timeout)
//        roomParamDto.setUserId(userId);

        return roomService.createRoom(roomParamDto);
    }

    @GetMapping("/rooms")
    public RoomResponseDto listRooms(@RequestParam int userId, HttpSession session) {
        // 이후 session 활용 -> userId 처리
//        int userId = ((UserDto)session.getAttribute("userDto")).getId(); // session 이 invalidate 된 상황 (timeout)
//        roomParamDto.setUserId(userId);

        return roomService.listRooms(userId);
    }

    @GetMapping("/rooms/{roomId}")
    public RoomResponseDto detailRoom(@PathVariable("roomId") int roomId) {
        RoomParamDto roomParamDto = new RoomParamDto();
        roomParamDto.setRoomId(roomId);
        return roomService.detailRoom(roomParamDto);
    }
}
