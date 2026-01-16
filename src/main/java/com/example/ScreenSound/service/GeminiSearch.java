package com.example.ScreenSound.service;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class GeminiSearch {
    public String obtainsInfo(String prompt){
        GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey("AIzaSyC4KPGotwRt7obUvdTajs8Ac0Rwxfz6DKc")
                .modelName("gemini-3-flash-preview")
                .timeout(Duration.ofSeconds(60))
                .build();
        return model.generate("Who is " + prompt + "?");
    }
}
