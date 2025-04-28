package com.mycom.myapp.invitation.service;

import com.mycom.myapp.config.exception.InvitationExceptions;
import com.mycom.myapp.config.exception.RoomExceptions;
import com.mycom.myapp.config.exception.UserExceptions;
import com.mycom.myapp.invitation.dto.InvitationAcceptResponse;
import com.mycom.myapp.invitation.dto.InvitationDto;
import com.mycom.myapp.invitation.dto.InvitationListResponse;
import com.mycom.myapp.invitation.dto.InvitationRejectResponse;
import com.mycom.myapp.invitation.entity.Invitation;
import com.mycom.myapp.invitation.entity.InvitationType;
import com.mycom.myapp.invitation.mapper.InvitationMapper;
import com.mycom.myapp.room.entity.Room;
import com.mycom.myapp.room.entity.RoomMember;
import com.mycom.myapp.room.mapper.RoomMapper;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InvitationServiceImpl implements InvitationService{

    private final InvitationMapper invitationMapper;
    private final RoomMapper roomMapper;
    private final UserMapper userMapper;

    @Override
    public InvitationListResponse getInvitationsByUserId(int userId) {
        User user = userMapper.findById(userId);

        if(user == null) {
            throw new UserExceptions.UserNotFoundException(userId);
        }

        List<InvitationDto> roomInvitations = invitationMapper.findInvitationsByUserId(userId);

        return InvitationListResponse.builder()
                .invitations(roomInvitations)
                .message("call invitations success")
                .userId(user.getId())
                .build();
    }

    @Override
    public InvitationAcceptResponse acceptInvitation(int invitationId, int userId) {
        Invitation invitation = invitationMapper.findInvitationById(invitationId);
        if (invitation == null) {
            throw new InvitationExceptions.InvitationNotFoundException(invitationId);
        }

        Room room = roomMapper.findRoomById(invitation.getChatroomId());
        if (room == null) {
            throw new RoomExceptions.RoomNotFoundException(invitation.getChatroomId());
        }

        if (invitation.getInvitationType() != InvitationType.pending) {
            throw new InvitationExceptions.InvitationAlreadyProcessedException(invitationId);
        }

        invitation.setInvitationType(InvitationType.accepted);
        invitation.setRespondedAt(LocalDateTime.now());
        invitationMapper.updateInvitation(invitation);

        RoomMember roomMember = new RoomMember();
        roomMember.setRoomId(invitation.getChatroomId());
        roomMember.setUserId(userId);
        roomMember.setRole("멤버");

        int result = roomMapper.insertRoomMember(roomMember);

        if (result <= 0) {
            throw new RoomExceptions.RoomMemberAddException();
        }

        return InvitationAcceptResponse.builder()
                .message("초대를 수락하고 채팅방에 참여했습니다.")
                .roomId(room.getId())
                .roomName(room.getName())
                .build();
    }

    @Override
    public InvitationRejectResponse rejectInvitation(int invitationId, int userId) {
        Invitation invitation = invitationMapper.findInvitationById(invitationId);
        if (invitation == null) {
            throw new InvitationExceptions.InvitationNotFoundException(invitationId);
        }

        Room room = roomMapper.findRoomById(invitation.getChatroomId());
        if (room == null) {
            throw new RoomExceptions.RoomNotFoundException(invitation.getChatroomId());
        }

        if (invitation.getInvitationType() != InvitationType.pending) {
            throw new InvitationExceptions.InvitationAlreadyProcessedException(invitationId);
        }

        // 초대 상태 변경
        invitation.setInvitationType(InvitationType.rejected);
        invitation.setRespondedAt(LocalDateTime.now());
        invitationMapper.updateInvitation(invitation);

        return InvitationRejectResponse.builder()
                .message("초대를 거절했습니다.")
                .roomId(room.getId())
                .roomName(room.getName())
                .build();
    }

}
