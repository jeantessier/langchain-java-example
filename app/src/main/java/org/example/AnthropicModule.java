package org.example;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import io.github.cdimascio.dotenv.Dotenv;

public class AnthropicModule extends AbstractModule {
    @Provides
    ChatLanguageModel provideAnthropicChatLanguageModel(@Named("api-key") String apiKey) {
        return AnthropicChatModel.builder()
                .apiKey(apiKey)
                .build();
    }

    @Provides
    @Named("api-key")
    String provideApiKey(Dotenv dotenv) {
        return dotenv.get("ANTHROPIC_API_KEY");
    }
}
