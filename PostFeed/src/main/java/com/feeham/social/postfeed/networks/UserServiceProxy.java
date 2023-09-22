package com.feeham.social.postfeed.networks;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ACCOUNTS-APP", configuration = CustomErrorDecoder.class)
public interface UserServiceProxy {
    @GetMapping("/api/internal/user/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId);
}
