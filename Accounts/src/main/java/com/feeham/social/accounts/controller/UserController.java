package com.feeham.social.accounts.controller;

import com.feeham.social.accounts.model.dto.UserCreateDTO;
import com.feeham.social.accounts.model.dto.UserReadDTO;
import com.feeham.social.accounts.model.entity.User;
import com.feeham.social.accounts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/public/user/{email}")
    public ResponseEntity<UserReadDTO> getPublicUser(@PathVariable String email) {
        UserReadDTO user = userService.readUser(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/public/user")
    public ResponseEntity<String> createUser(@RequestBody UserCreateDTO userCreate) {
        if (userService.createUser(userCreate)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/public/user/{email}")
    public ResponseEntity<String> updatePublicUser(
            @PathVariable String email,
            @RequestBody UserCreateDTO userUpdateDTO) {
        if (userService.updateUser(email, userUpdateDTO)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/public/user/{email}")
    public ResponseEntity<String> deletePublicUser(@PathVariable String email) {
        if (userService.deleteUser(email)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/internal/user/{userId}")
    public ResponseEntity<User> getInternalUser(@PathVariable String userId) {
        User user = userService.getUser(UUID.fromString(userId));
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}