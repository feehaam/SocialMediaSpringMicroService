package com.feeham.social.postfeed.networks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private UUID userId;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
}
