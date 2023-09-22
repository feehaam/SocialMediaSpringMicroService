package com.feeham.social.accounts.service;

import com.feeham.social.accounts.model.dto.UserCreateDTO;
import com.feeham.social.accounts.model.dto.UserReadDTO;
import com.feeham.social.accounts.model.entity.User;

import java.util.UUID;

public interface UserService {
    public User getUser(UUID userId);
    public UserReadDTO readUser(String email);
    public boolean createUser(UserCreateDTO userCreate);
    public boolean updateUser(String email, UserCreateDTO userUpdateDTO);
    public boolean deleteUser(String email);
}
