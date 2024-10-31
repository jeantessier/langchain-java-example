package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final String OPENAI_MODEL = "openai";
    private static final String ANTHROPIC_MODEL = "anthropic";
    private static final String DEFAULT_MODEL = OPENAI_MODEL;

    public static void main(String[] args) {
        List<String> argsList = Arrays.asList(args);
        int modelNameIndex = argsList.indexOf("--model");
        String modelNameValue = (modelNameIndex >= 0) ? argsList.get(modelNameIndex + 1) : DEFAULT_MODEL;

        com.google.inject.Module aiModule = switch (modelNameValue) {
            case OPENAI_MODEL -> new OpenAiModule();
            case ANTHROPIC_MODEL -> new AnthropicModule();
            default -> throw new IllegalStateException("Unexpected value: " + modelNameValue);
        };

        Injector injector = Guice.createInjector(new DotenvModule(), aiModule);
        App app = injector.getInstance(App.class);
        System.out.println(app.getGreeting());
    }
}
