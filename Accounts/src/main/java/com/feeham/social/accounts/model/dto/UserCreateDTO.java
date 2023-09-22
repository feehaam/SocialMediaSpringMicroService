package com.feeham.social.accounts.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserCreateDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
