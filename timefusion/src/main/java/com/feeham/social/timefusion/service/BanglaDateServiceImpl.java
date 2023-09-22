package com.feeham.social.timefusion.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class BanglaDateServiceImpl implements BanglaDateService{
    private static final String[] BANGLA_MONTH_NAMES = {
            "বৈশাখ", "জ্যৈষ্ঠ", "আষাঢ়", "শ্রাবণ", "ভাদ্র", "আশ্বিন", "কার্তিক", "অগ্রহায়ণ", "পৌষ", "মাঘ", "ফাল্গুন", "চৈত্র"
    };
    private static final Map<Integer, String> BANGLA_DIGITS = new HashMap<>();
    static {
        BANGLA_DIGITS.put(0, "০");
        BANGLA_DIGITS.put(1, "১");
        BANGLA_DIGITS.put(2, "২");
        BANGLA_DIGITS.put(3, "৩");
        BANGLA_DIGITS.put(4, "৪");
        BANGLA_DIGITS.put(5, "৫");
        BANGLA_DIGITS.put(6, "৬");
        BANGLA_DIGITS.put(7, "৭");
        BANGLA_DIGITS.put(8, "৮");
        BANGLA_DIGITS.put(9, "৯");
    }
    public String getDtateTime(LocalDateTime gregorianDateTime) {
        String banglaDate = getDate(gregorianDateTime.toLocalDate());
        int hour = gregorianDateTime.getHour();
        int minute = gregorianDateTime.getMinute();

        String banglaTime = convertToBanglaDigits(hour) + " ঘণ্টা " + convertToBanglaDigits(minute) + " মিনিট";
        String banglaDateTime = banglaDate + ", " + banglaTime;

        return banglaDateTime;
    }
    private static String convertToBanglaDigits(int number) {
        StringBuilder banglaNumber = new StringBuilder();
        String numberStr = Integer.toString(number);
        for (char digit : numberStr.toCharArray()) {
            banglaNumber.append(BANGLA_DIGITS.get(Character.getNumericValue(digit)));
        }
        return banglaNumber.toString();
    }

    public String getDate(LocalDate gregorianDate) {
        int gregorianYear = gregorianDate.getYear();
        int gregorianMonth = gregorianDate.getMonthValue();
        int gregorianDay = gregorianDate.getDayOfMonth();

        int banglaYear = gregorianYear - 594;

        String banglaYearStr = convertToBanglaDigits(banglaYear);
        String banglaMonthStr = BANGLA_MONTH_NAMES[gregorianMonth - 1];
        String banglaDayStr = convertToBanglaDigits(gregorianDay);

        return banglaDayStr + " " + banglaMonthStr + ", " + banglaYearStr + " বঙ্গাব্দ";
    }
}
