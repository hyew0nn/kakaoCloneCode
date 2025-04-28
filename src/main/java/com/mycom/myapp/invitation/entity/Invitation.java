package com.mycom.myapp.invitation.entity;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Invitation {
    private int id;
    private int chatroomId;
    private int inviterId;
    private int inviteeId;
    private InvitationType invitationType;
    private LocalDateTime invitedAt;
    private LocalDateTime respondedAt;
}
