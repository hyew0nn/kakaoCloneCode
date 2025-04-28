package com.mycom.myapp.invitaiton.entity;

public enum InvitationType {
    pending,
    accepted,
    rejected;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
