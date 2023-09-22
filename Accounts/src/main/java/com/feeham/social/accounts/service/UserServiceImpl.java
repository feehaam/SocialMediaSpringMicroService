package com.feeham.social.accounts.service;

import com.feeham.social.accounts.model.dto.UserCreateDTO;
import com.feeham.social.accounts.model.dto.UserReadDTO;
import com.feeham.social.accounts.model.entity.User;
import com.feeham.social.accounts.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public User getUser(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    @Override
    public UserReadDTO readUser(String email) {
        Optional<User> userOp = userRepository.findByEmail(email);
        if (userOp.isEmpty()) {
            return null;
        }
        User user = userOp.get();
        return new UserReadDTO(user.getEmail(), user.getUserId().toString(), user.getFirstName(), user.getLastName());
    }

    @Override
    public boolean createUser(UserCreateDTO userCreate) {
        User user = new User();
        user.setEmail(userCreate.getEmail());
        user.setPasswordHash(encoder.encode(userCreate.getPassword()));
        user.setFirstName(userCreate.getFirstName());
        user.setLastName(userCreate.getLastName());
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean updateUser(String email, UserCreateDTO userUpdateDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userUpdateDTO.getFirstName());
            user.setLastName(userUpdateDTO.getLastName());
            userRepository.save(user);
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteUser(String email) {
        Optional<User> userOp = userRepository.findByEmail(email);
        if (userOp.isEmpty()) {
            return false;
        }
        userRepository.deleteById(userOp.get().getUserId());
        return true;
    }
}
