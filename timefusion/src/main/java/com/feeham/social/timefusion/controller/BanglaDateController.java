package com.feeham.social.timefusion.controller;

import com.feeham.social.timefusion.service.BanglaDateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/bangla")
public class BanglaDateController {

    private final BanglaDateService banglaDateService;

    public BanglaDateController(BanglaDateService banglaDateService) {
        this.banglaDateService = banglaDateService;
    }

    @GetMapping("/date-time")
    public String convertToBanglaDateTime(@RequestParam LocalDateTime gregorianDateTime) {
        return banglaDateService.getDtateTime(gregorianDateTime);
    }

    @GetMapping("/date")
    public String convertToBanglaDate(@RequestParam LocalDate gregorianDate) {
        return banglaDateService.getDate(gregorianDate);
    }
}
