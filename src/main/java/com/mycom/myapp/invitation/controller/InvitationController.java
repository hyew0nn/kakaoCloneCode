package com.mycom.myapp.invitation.controller;

import com.mycom.myapp.invitation.dto.InvitationAcceptResponse;
import com.mycom.myapp.invitation.dto.InvitationListResponse;
import com.mycom.myapp.invitation.dto.InvitationRejectResponse;
import com.mycom.myapp.invitation.service.InvitationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invitations")
public class InvitationController {
    private final InvitationService invitationService;

    @GetMapping
    public ResponseEntity<InvitationListResponse> getInvitationsByUserId(HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        InvitationListResponse invitationListResponse = invitationService.getInvitationsByUserId(userId);
        return ResponseEntity.ok(invitationListResponse);
    }

    @PostMapping("/{invite}/accept")
    public ResponseEntity<InvitationAcceptResponse> acceptInvitation(@PathVariable("invite") int inviteId, HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        InvitationAcceptResponse invitationAcceptResponse = invitationService.acceptInvitation(inviteId, userId);
        return ResponseEntity.ok(invitationAcceptResponse);
    }

    @PostMapping("/{invite}/reject")
    public ResponseEntity<InvitationRejectResponse> rejectInvitation(@PathVariable("invite") int inviteId, HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        InvitationRejectResponse invitationRejectResponse = invitationService.rejectInvitation(inviteId, userId);
        return ResponseEntity.ok(invitationRejectResponse);
    }
}
