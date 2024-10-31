/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AppTest {
    static Stream<Arguments> dataProvider() {
        return Stream.of(
                arguments("openai", App.getOpenAiChatLanguageModel()),
                arguments("anthropic", App.getAnthropicChatLanguageModel())
        );
    }

    @DisplayName("with chat model")
    @ParameterizedTest(name="should have a greeting with model {0} ")
    @MethodSource("dataProvider")
    void appWithChatModel(String variation, ChatLanguageModel chatLanguageModel) {
        // Given
        var sut = new App(chatLanguageModel);

        // When
        var actualGreeting = sut.getGreeting();

        // Then
        assertNotNull(actualGreeting, "app should have a greeting");
    }
}
