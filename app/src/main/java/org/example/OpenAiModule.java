package org.example;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import io.github.cdimascio.dotenv.Dotenv;

import static dev.langchain4j.model.openai.OpenAiChatModelName.*;

public class OpenAiModule extends AbstractModule {
    @Provides
    ChatLanguageModel provideChatLanguageModel(@ApiKey String apiKey, OpenAiChatModelName modelName) {
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
    OpenAiChatModelName provideModelName() {
//        return GPT_3_5_TURBO;
        return GPT_4_O_MINI;
    }
}
