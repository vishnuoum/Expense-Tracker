package com.app.expense_tracker.exception_manager;

import com.app.expense_tracker.controllers.response.ErrorResponse;
import com.app.expense_tracker.settings.ServiceManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TrackerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = TrackerException.class)
    protected ResponseEntity<Object> handleException(TrackerException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ex.getErrorCode());
        errorResponse.setErrorMessage(ex.getErrorMessage());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatusCode());
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleRuntimeException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ServiceManager.ErrorCode.UNKNOWN_ERROR);
        errorResponse.setErrorMessage(ServiceManager.ErrorMessage.UNKNOWN_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
