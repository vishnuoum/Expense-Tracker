package com.app.expense_tracker.exception_manager;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class TrackerException extends RuntimeException{

    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatusCode;

    private TrackerException(){}

    TrackerException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    TrackerException(String errorCode, String errorMessage, HttpStatus httpStatusCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
    }

}
