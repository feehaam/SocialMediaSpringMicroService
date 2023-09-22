package com.feeham.social.timefusion.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BanglaDateService {
    public String getDtateTime(LocalDateTime gregorianDateTime);
    public String getDate(LocalDate gregorianDate);
}
