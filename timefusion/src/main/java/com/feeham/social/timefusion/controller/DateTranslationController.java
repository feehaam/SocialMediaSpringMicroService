package com.feeham.social.timefusion.controller;

import com.feeham.social.timefusion.service.TimeTranslator;
import com.feeham.social.timefusion.utilities.TranslatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class DateTranslationController {

    @Autowired
    private TranslatorFactory translatorFactory;

    @GetMapping("/translate")
    public String translateDate(@RequestParam String language, @RequestParam String dateTime) {
        try {
            LocalDateTime inputDateTime = LocalDateTime.parse(dateTime);
            TimeTranslator translator = translatorFactory.getInstance(language);
            return translator.translate(inputDateTime);
        } catch (Exception e) {
            return "Invalid input format or language.";
        }
    }
}
