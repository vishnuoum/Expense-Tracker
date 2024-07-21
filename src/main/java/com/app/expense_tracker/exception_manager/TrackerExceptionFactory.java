package com.app.expense_tracker.exception_manager;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class TrackerExceptionFactory {

    private static Map<String, TrackerException> errorMappings = new HashMap<>();

    public void add(String errorCode, String errorMessage, HttpStatus httpsStatusCode) {
        errorMappings.put(errorCode, new TrackerException(errorCode, errorMessage, httpsStatusCode));
    }

    public static TrackerException throwException(String errorCode) {
        return errorMappings.get(errorCode);
    }

    public static TrackerException throwCustomizedException(String errorCode, String errorMessage, HttpStatus httpsStatusCode) {
        return new TrackerException(errorCode, errorMessage, httpsStatusCode);
    }

    public static TrackerException throwCustomizedException(String errorCode, String errorMessage, HttpStatus httpsStatusCode, Object... args) {
        return new TrackerException(errorCode, errorMessage.formatted(args), httpsStatusCode);
    }
}
