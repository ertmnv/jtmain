package com.iverbs.jtmain.shared;

import java.util.Date;
import java.util.Map;

public class ApiError {

    private long timestamp = new Date().getTime();

    private int status;

    private String message;

    private String url;

    private Map<String, String> validationErrors;

    public ApiError(int status, String message, String url) {
        super();
        this.status = status;
        this.message = message;
        this.url = url;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

}
