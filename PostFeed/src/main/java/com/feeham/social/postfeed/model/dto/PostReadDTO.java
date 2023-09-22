package com.feeham.social.postfeed.model.dto;

import lombok.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class PostReadDTO {
    private String content;
    private String userFirstName;
    private String userLastName;
    private UnifiedTime unifiedTime;
}
