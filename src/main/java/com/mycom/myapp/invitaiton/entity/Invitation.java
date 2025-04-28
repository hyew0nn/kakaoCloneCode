package com.mycom.myapp.invitaiton.entity;

import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
