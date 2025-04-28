package com.mycom.myapp.invitation.service;

import com.mycom.myapp.invitation.dto.InvitationAcceptResponse;
import com.mycom.myapp.invitation.dto.InvitationListResponse;
import com.mycom.myapp.invitation.dto.InvitationRejectResponse;

public interface InvitationService {
    InvitationListResponse getInvitationsByUserId(int userId);
    InvitationAcceptResponse acceptInvitation(int invitationId, int userId);
    InvitationRejectResponse rejectInvitation(int invitationId, int userId);
}
