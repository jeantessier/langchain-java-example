package org.example;

import com.google.inject.Inject;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.structured.StructuredPrompt;
import dev.langchain4j.model.input.structured.StructuredPromptProcessor;

import java.util.List;

import static java.util.Arrays.asList;

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
    private static class CreateRecipePrompt {
        private String dish;
        private List<String> ingredients;
    }

    public String getRecipe() {
        CreateRecipePrompt createRecipePrompt = new CreateRecipePrompt();
        createRecipePrompt.dish = "salad";
        createRecipePrompt.ingredients = asList("cucumber", "tomato", "feta", "onion", "olives");
        Prompt prompt = StructuredPromptProcessor.toPrompt(createRecipePrompt);

        AiMessage aiMessage = chatModel.chat(prompt.toUserMessage()).aiMessage();

        return aiMessage.text();
    }
}
