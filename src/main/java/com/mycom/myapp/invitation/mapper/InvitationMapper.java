package com.mycom.myapp.invitation.mapper;

import com.mycom.myapp.invitation.dto.InvitationDto;
import com.mycom.myapp.invitation.entity.Invitation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvitationMapper {
    int insertInvitation(Invitation invitation);

    Invitation findInvitationById(int invitationId);
    void updateInvitation(Invitation invitation);

    List<InvitationDto> findInvitationsByUserId(int userId);
}
