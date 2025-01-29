package org.example;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import io.github.cdimascio.dotenv.Dotenv;

public class GoogleModule extends AbstractModule {
    @Provides
    ChatLanguageModel provideGooleAiGeminiChatModel(@ApiKey String apiKey, @ModelName String modelName) {
        return GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .build();
    }

    @Provides
    @ApiKey
    String provideApiKey(Dotenv dotenv) {
        return dotenv.get("GOOGLE_API_KEY");
    }

    @Provides
    @ModelName
    String provideModelName(Dotenv dotenv) {
        return dotenv.get("GOOGLE_MODEL");
    }
}
