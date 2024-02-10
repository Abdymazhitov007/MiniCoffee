package org.example.coffeee.utils;

import lombok.NoArgsConstructor;

import java.util.ResourceBundle;

@NoArgsConstructor
public class ResourceBundleLanguage {

    private static final ResourceBundle RU = ResourceBundle.getBundle("language.ru");
    private static final ResourceBundle EN = ResourceBundle.getBundle("language.en");

    public static String periodMessage(Language language, String key) {

        switch (language) {
            case RU -> {
                return RU.getString(key);
            }
            case EN -> {
                return EN.getString(key);
            }
            default -> {
                return EN.getString(key);
            }
        }
    }
}
