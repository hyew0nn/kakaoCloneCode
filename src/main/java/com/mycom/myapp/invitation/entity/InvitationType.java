package com.mycom.myapp.invitation.entity;

public enum InvitationType {
    pending,
    accepted,
    rejected;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
