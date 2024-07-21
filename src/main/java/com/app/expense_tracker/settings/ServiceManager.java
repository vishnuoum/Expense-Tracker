package com.app.expense_tracker.settings;

import com.app.expense_tracker.exception_manager.TrackerExceptionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ServiceManager {

    public static class ErrorCode {
        public static final String USER_SIGNUP_ERROR = "USER01";
        public static final String USER_LOGIN_ERROR = "USER02";
        public static final String USER_PROFILE_UPDATE_ERROR = "USER03";
        public static final String DUPLICATE_PHONE_ERROR = "USER04";
        public static final String USER_NOT_FOUND = "USER05";
        public static final String GENERIC_ERROR = "ERROR";
        public static final String INVALID_REQUEST_ERROR = "ERROR400";
        public static final String UNKNOWN_ERROR = "ERROR_UNKNOWN";
    }

    public static class ErrorMessage {
        public static final String USER_SIGNUP_ERROR = "Error signing up the user";
        public static final String USER_LOGIN_ERROR = "Error while user logging in";
        public static final String USER_PROFILE_UPDATE_ERROR = "Error while updating user info: %s";
        public static final String DUPLICATE_PHONE_ERROR = "Phone no. already exists";
        public static final String USER_NOT_FOUND = "User not found";
        public static final String GENERIC_ERROR = "Generic Error has occurred";
        public static final String INVALID_REQUEST_ERROR = "Invalid request. Please provide a valid request";
        public static final String UNKNOWN_ERROR = "An unknown error has occurred";
    }

    static {
        TrackerExceptionFactory exceptionFactory = new TrackerExceptionFactory();

        exceptionFactory.add(ErrorCode.USER_SIGNUP_ERROR,ErrorMessage.USER_SIGNUP_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionFactory.add(ErrorCode.USER_LOGIN_ERROR,ErrorMessage.USER_LOGIN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionFactory.add(ErrorCode.GENERIC_ERROR,ErrorMessage.GENERIC_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionFactory.add(ErrorCode.DUPLICATE_PHONE_ERROR,ErrorMessage.DUPLICATE_PHONE_ERROR, HttpStatus.CONFLICT);
        exceptionFactory.add(ErrorCode.INVALID_REQUEST_ERROR,ErrorMessage.INVALID_REQUEST_ERROR, HttpStatus.BAD_REQUEST);
        exceptionFactory.add(ErrorCode.USER_NOT_FOUND,ErrorMessage.USER_NOT_FOUND, HttpStatus.OK);
    }
}
