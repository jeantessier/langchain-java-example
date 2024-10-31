package org.example;

import com.google.inject.Guice;
import dev.ai4j.openai4j.OpenAiHttpException;
import dev.langchain4j.model.anthropic.internal.client.AnthropicHttpException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AppTest {
    static Stream<Arguments> usedQuotaCases() {
        return Stream.of(
                arguments("openai", new OpenAiModule(), OpenAiHttpException.class, "You exceeded your current quota"),
                arguments("anthropic", new AnthropicModule(), AnthropicHttpException.class, "Your credit balance is too low")
        );
    }

    static Stream<Arguments> noQuotaCases() {
        return Stream.of(
                arguments("google", new GoogleModule(), "It's great to hear from you")
        );
    }

    @DisplayName("with valid license, no credit")
    @ParameterizedTest(name="with {0} ")
    @MethodSource("usedQuotaCases")
    void callChatModelWithError(String variation, com.google.inject.Module aiModule, Class<? extends Exception> exceptionClass, String expectedMessage) {
        // Given
        var injector = Guice.createInjector(new DotenvModule(), aiModule);
        var sut = injector.getInstance(App.class);

        // When
        var exception = assertThrows(RuntimeException.class, sut::getGreeting);

        // Then
        assertEquals(exceptionClass, exception.getCause().getClass());

        // And
        var failureMessage = exception.getCause().getMessage();
        assertTrue(failureMessage.toLowerCase().contains(variation), failureMessage);
        assertTrue(failureMessage.contains(expectedMessage), failureMessage);
    }

    @DisplayName("with valid license, no quota")
    @ParameterizedTest(name="with {0} ")
    @MethodSource("noQuotaCases")
    void callChatModelWithSuccess(String variation, com.google.inject.Module aiModule, String expectedMessage) {
        // Given
        var injector = Guice.createInjector(new DotenvModule(), aiModule);
        var sut = injector.getInstance(App.class);

        // When
        var actualMessage = sut.getGreeting();

        // Then
        assertTrue(actualMessage.contains(expectedMessage), actualMessage);
    }
}
