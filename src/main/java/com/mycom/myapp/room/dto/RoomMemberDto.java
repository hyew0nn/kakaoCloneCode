package com.mycom.myapp.room.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoomMemberDto {
    private int userId;
    private String userName;
    private String memberRole;
}
