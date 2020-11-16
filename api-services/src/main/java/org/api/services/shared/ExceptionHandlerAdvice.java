package org.api.services.shared;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final String PERMISSION_DENIED = "Permission Denied";
    private static final String VALIDATION_ERROR = "Validation error";
    private static final int BAD_REQUEST_STATUS = 400;

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleValidationException(final MethodArgumentNotValidException exception,
            final HttpServletRequest request) {
        ApiError apiError = new ApiError(BAD_REQUEST_STATUS, VALIDATION_ERROR, request.getServletPath());
        BindingResult result = exception.getBindingResult();
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        apiError.setValidationErrors(validationErrors);
        return apiError;
    }

    @ExceptionHandler({ PermissionDeniedException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handlePermissionDeniedException(final PermissionDeniedException exception,
            final HttpServletRequest request) {
        ApiError apiError = new ApiError(BAD_REQUEST_STATUS, PERMISSION_DENIED, request.getServletPath());
        return apiError;
    }
}
