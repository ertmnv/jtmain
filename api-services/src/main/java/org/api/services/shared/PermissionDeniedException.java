package org.api.services.shared;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(final String errorMessage) {
        super(errorMessage);
    }
}
