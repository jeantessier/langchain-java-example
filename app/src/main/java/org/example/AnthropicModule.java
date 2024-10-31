package org.example;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import io.github.cdimascio.dotenv.Dotenv;

public class AnthropicModule extends AbstractModule {
    @Provides
    ChatLanguageModel provideAnthropicChatLanguageModel(@ApiKey String apiKey) {
        return AnthropicChatModel.builder()
                .apiKey(apiKey)
                .build();
    }

    @Provides
    @ApiKey
    String provideApiKey(Dotenv dotenv) {
        return dotenv.get("ANTHROPIC_API_KEY");
    }
}
