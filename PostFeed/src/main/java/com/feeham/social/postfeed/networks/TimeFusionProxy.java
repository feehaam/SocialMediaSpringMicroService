package com.feeham.social.postfeed.networks;

import com.feeham.social.postfeed.model.dto.UnifiedTime;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TIME-FUSION-APP", configuration = CustomErrorDecoder.class)
public interface TimeFusionProxy {
    @GetMapping("/api/unifiedTime/{dateTime}")
    public ResponseEntity<UnifiedTime> getUnifiedTime(@PathVariable String dateTime);
}
