package com.mycom.myapp.room.controller;

import com.mycom.myapp.room.dto.*;
import com.mycom.myapp.room.service.RoomService;
import com.mycom.myapp.user.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomInsertResponse> createRoom(RoomInsertRequest roomInsertRequest, HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        roomInsertRequest.setUserId(userId);

        RoomInsertResponse roomInsertResponse = roomService.createRoom(roomInsertRequest);

        return ResponseEntity.ok(roomInsertResponse);
    }

    @GetMapping
    public ResponseEntity<RoomListResponse> getRoomsByUserId(HttpSession session) {
        int userId = (int) session.getAttribute("userId");

        RoomListResponse roomListResponse = roomService.getRoomsByUserId(userId);
        return ResponseEntity.ok(roomListResponse);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDetailResponse> getRoomDetails(@PathVariable("roomId") int roomId) {
        RoomDetailResponse roomDetailResponse = roomService.getRoomDetails(roomId);
        return ResponseEntity.ok(roomDetailResponse);
    }

    @PostMapping("/{roomId}/invite")
    public ResponseEntity<RoomInviteResponse> inviteToRoom(@PathVariable("roomId") int roomId, RoomInviteRequest roomInviteRequest, HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        roomInviteRequest.setInviterId(userId);
        RoomInviteResponse roomInviteResponse = roomService.inviteToRoom(roomInviteRequest, roomId);
        return ResponseEntity.ok(roomInviteResponse);
    }
}
