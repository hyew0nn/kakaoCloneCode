package com.mycom.myapp.invitaiton.service;

import com.mycom.myapp.invitaiton.dto.InvitationAcceptResponse;
import com.mycom.myapp.invitaiton.dto.InvitationListResponse;
import com.mycom.myapp.invitaiton.dto.InvitationRejectResponse;

public interface InvitationService {
    InvitationListResponse getInvitationsByUserId(int userId);
    InvitationAcceptResponse acceptInvitation(int invitationId, int userId);
    InvitationRejectResponse rejectInvitation(int invitationId, int userId);
}
