package org.api.services.shared;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String errorMessage) {
        super(errorMessage);
    }
}