package com.app.expense_tracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordUpdateDto {

    private String id;
    private String oldPassword;
    private String newPassword;
}
