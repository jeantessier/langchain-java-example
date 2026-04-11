package org.example;

import com.google.inject.Inject;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.input.structured.StructuredPrompt;
import dev.langchain4j.model.input.structured.StructuredPromptProcessor;

import java.util.List;

public class App {
    private final ChatModel chatModel;

    @Inject
    public App(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getGreeting() {
        return chatModel.chat("Hello world!");
    }

    @StructuredPrompt("Create a recipe for a {{dish}} that can be prepared using only {{ingredients}}")
    private record CreateRecipePrompt(String dish, List<String> ingredients) {}

    public String getRecipe() {
        var createRecipePrompt = new CreateRecipePrompt("salad", List.of("cucumber", "tomato", "feta", "onion", "olives"));
        var prompt = StructuredPromptProcessor.toPrompt(createRecipePrompt);

        var aiMessage = chatModel.chat(prompt.toUserMessage()).aiMessage();

        return aiMessage.text();
    }
}
