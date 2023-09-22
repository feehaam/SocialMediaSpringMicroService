package com.feeham.social.timefusion.service;

import java.time.LocalDateTime;

public interface TimeTranslator {
    String translate(LocalDateTime dateTime);
}

