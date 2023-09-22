package com.feeham.social.accounts.model.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UserReadDTO {
    private String email;
    private String userId;
    private String firstName;
    private String lastName;
}
