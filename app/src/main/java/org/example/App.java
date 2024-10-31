package org.example;

import com.google.inject.Inject;
import dev.langchain4j.model.chat.ChatLanguageModel;

public class App {
    private final ChatLanguageModel chatLanguageModel;

    @Inject
    public App(ChatLanguageModel chatLanguageModel) {
        this.chatLanguageModel = chatLanguageModel;
    }

    public String getGreeting() {
        return chatLanguageModel.generate("Hello world!");
    }
}
