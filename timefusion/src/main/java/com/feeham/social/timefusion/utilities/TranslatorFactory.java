package com.feeham.social.timefusion.utilities;

import com.feeham.social.timefusion.service.TimeTranslator;
import com.feeham.social.timefusion.service.TimeTranslatorBangla;
import com.feeham.social.timefusion.service.TimeTranslatorEnglish;
import org.springframework.stereotype.Component;

@Component
public class TranslatorFactory {
    private final TimeTranslator banglaTranslator = new TimeTranslatorBangla();
    private final TimeTranslator englishTranslator = new TimeTranslatorEnglish();
    public TimeTranslator getInstance(String language){
        if(language.toLowerCase().equals("bn")) return banglaTranslator;
        return englishTranslator;
    }
}
