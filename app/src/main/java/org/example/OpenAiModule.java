package org.example;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import io.github.cdimascio.dotenv.Dotenv;

import static dev.langchain4j.model.openai.OpenAiChatModelName.*;

public class OpenAiModule extends AbstractModule {
    @Provides
    ChatLanguageModel provideChatLanguageModel(@ApiKey String apiKey, @ModelName String modelName) {
        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .build();
    }

    @Provides
    @ApiKey
    String provideApiKey(Dotenv dotenv) {
        return dotenv.get("OPENAI_API_KEY");
    }

    @Provides
    @ModelName
    String provideModelName(Dotenv dotenv) {
        return dotenv.get("OPENAI_MODEL");
    }
}
