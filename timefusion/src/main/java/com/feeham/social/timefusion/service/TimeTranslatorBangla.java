package com.feeham.social.timefusion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TimeTranslatorBangla implements TimeTranslator {
    @Autowired
    private BanglaDateService banglaDateService;

    @Override
    public String translate(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, now);

        if (duration.toMinutes() < 1) {
            return "এই মুহূর্তে";
        } else if (duration.toHours() < 1) {
            long minutes = duration.toMinutes();
            return convertToBanglaDigits(minutes) + " মিনিট আগে";
        } else if (duration.toHours() < 24) {
            long hours = duration.toHours();
            return convertToBanglaDigits(hours) + " ঘণ্টা আগে";
        } else if (duration.toDays() < 7) {
            long days = duration.toDays();
            return convertToBanglaDigits(days) + " দিন আগে";
        } else if (duration.toDays() < 28) {
            long weeks = duration.toDays() / 7;
            return convertToBanglaDigits(weeks) + " সপ্তাহ আগে";
        } else {
            return banglaDateService.getDtateTime(dateTime);
        }
    }

    private static String convertToBanglaDigits(long number) {
        String[] banglaDigits = {"০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"};
        StringBuilder banglaNumber = new StringBuilder();
        String numberStr = Long.toString(number);
        for (char digit : numberStr.toCharArray()) {
            banglaNumber.append(banglaDigits[Character.getNumericValue(digit)]);
        }
        return banglaNumber.toString();
    }
}
