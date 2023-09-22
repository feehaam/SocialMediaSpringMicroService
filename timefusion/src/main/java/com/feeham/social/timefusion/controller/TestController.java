package com.feeham.social.timefusion.controller;

import com.feeham.social.timefusion.service.BanglaDateService;
import com.feeham.social.timefusion.service.TimeTranslator;
import com.feeham.social.timefusion.utilities.TranslatorFactory;
import com.feeham.social.timefusion.utilities.UnifiedTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestController {

    @Autowired
    private TranslatorFactory translatorFactory;
    @Autowired
    private BanglaDateService banglaDateService;
    @Autowired
    Environment environment;

    @GetMapping("/api/unifiedTime/{dateTime}")
    public UnifiedTime getUnifiedTime(@PathVariable String dateTime) {
        String port = environment.getProperty("local.server.port");
        try {
            LocalDateTime inputDateTime = LocalDateTime.parse(dateTime);
            TimeTranslator englishTranslator = translatorFactory.getInstance("en");
            TimeTranslator banglaTranslator = translatorFactory.getInstance("bn");

            return new UnifiedTime(
                    inputDateTime.toString(),
                    englishTranslator.translate(inputDateTime),
                    banglaDateService.getDtateTime(inputDateTime),
                    banglaTranslator.translate(inputDateTime),
                    port
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid input format or language.");
        }
    }
}
