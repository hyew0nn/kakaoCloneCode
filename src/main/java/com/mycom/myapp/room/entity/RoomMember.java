package com.mycom.myapp.room.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomMember {
    private int id;
    private int roomId;
    private int userId;
    private String role;
    LocalDateTime joinedAt;
}
