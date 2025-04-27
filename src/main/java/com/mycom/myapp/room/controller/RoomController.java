package com.mycom.myapp.room.controller;

import com.mycom.myapp.room.dto.RoomDetailResponse;
import com.mycom.myapp.room.dto.RoomInsertRequest;
import com.mycom.myapp.room.dto.RoomInsertResponse;
import com.mycom.myapp.room.dto.RoomsListResponse;
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
    public ResponseEntity<RoomInsertResponse> insertRoom(RoomInsertRequest roomInsertRequest, HttpSession session) {

//        int userId = ((UserDto)session.getAttribute("userDto")).getId();
//        roomInsertRequest.setUserId(userId);

        RoomInsertResponse roomInsertResponse = roomService.insertRoom(roomInsertRequest);

        return ResponseEntity.ok(roomInsertResponse);
    }

    @GetMapping
    public ResponseEntity<RoomsListResponse> listRooms(@RequestParam int userId, HttpSession session) {

//        int userId = ((UserDto)session.getAttribute("userDto")).getId();
//        roomParamDto.setUserId(userId);

        RoomsListResponse roomsListResponse = roomService.listRooms(userId);
        return ResponseEntity.ok(roomsListResponse);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDetailResponse> detailRoom(@PathVariable("roomId") int roomId) {
        RoomDetailResponse roomDetailResponse = roomService.detailRoom(roomId);
        return ResponseEntity.ok(roomDetailResponse);
    }
}
