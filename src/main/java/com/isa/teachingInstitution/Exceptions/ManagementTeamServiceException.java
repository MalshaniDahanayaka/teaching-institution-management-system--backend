package com.isa.teachingInstitution.Exceptions;

public class ManagementTeamServiceException extends RuntimeException {
    public ManagementTeamServiceException(String message, Exception e) {
        super(message, e);
    }
}