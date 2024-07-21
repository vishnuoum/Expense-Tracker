package com.app.expense_tracker.controllers.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
}
