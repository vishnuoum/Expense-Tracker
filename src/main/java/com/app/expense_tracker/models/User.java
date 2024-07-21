package com.app.expense_tracker.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String id;
    private String username;
    private String phone;
    private String password;
}
