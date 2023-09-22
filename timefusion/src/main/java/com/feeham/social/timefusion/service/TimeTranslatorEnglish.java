package com.feeham.social.timefusion.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TimeTranslatorEnglish implements TimeTranslator{
    @Override
    public String translate(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, now);

        if (duration.toMinutes() < 1) {
            return "Just now";
        } else if (duration.toHours() < 1) {
            long minutes = duration.toMinutes();
            return minutes + " minute" + (minutes > 1 ? "s" : "") + " ago";
        } else if (duration.toHours() < 24) {
            long hours = duration.toHours();
            return hours + " hour" + (hours > 1 ? "s" : "") + " ago";
        } else if (duration.toDays() < 7) {
            long days = duration.toDays();
            return days + " day" + (days > 1 ? "s" : "") + " ago";
        } else if (duration.toDays() < 28) {
            long weeks = duration.toDays() / 7;
            return weeks + " week" + (weeks > 1 ? "s" : "") + " ago";
        } else {
            return dateTime.toString();
        }
    }
}

