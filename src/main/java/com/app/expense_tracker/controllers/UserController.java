package com.app.expense_tracker.controllers;

import com.app.expense_tracker.dto.PasswordUpdateDto;
import com.app.expense_tracker.dto.PhoneUpdateDto;
import com.app.expense_tracker.models.User;
import com.app.expense_tracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return new ResponseEntity<>(userService.login(user), HttpStatus.OK);
    }

    @PutMapping("/updatePhone")
    public ResponseEntity updatePhone(@RequestBody PhoneUpdateDto updateDto) {
        userService.updatePhone(updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity updatePassword(@RequestBody PasswordUpdateDto updateDto) {
        userService.updatePassword(updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
