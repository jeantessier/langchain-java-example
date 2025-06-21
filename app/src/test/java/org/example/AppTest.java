package org.example;

import com.google.inject.Guice;
import dev.langchain4j.exception.RateLimitException;
import dev.langchain4j.exception.InvalidRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AppTest {
    static Stream<Arguments> usedQuotaCases() {
        return Stream.of(
//                arguments("openai", new OpenAiModule(), RateLimitException.class, "You exceeded your current quota"),
                arguments("anthropic", new AnthropicModule(), InvalidRequestException.class, "Your credit balance is too low")
        );
    }

    static Stream<Arguments> noQuotaCases() {
        return Stream.of(
                arguments("openai", new OpenAiModule(), "Hello"),
//                arguments("google", new GoogleModule(), "It's great to hear from you")
                arguments("google", new GoogleModule(), "Hello")
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
        assertEquals(exceptionClass, exception.getClass());

        // And
        var failureMessage = exception.getMessage();
        assertThat(failureMessage.toLowerCase()).contains(variation);
        assertThat(failureMessage).contains(expectedMessage);
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
        assertThat(actualMessage).contains(expectedMessage);
    }
}
