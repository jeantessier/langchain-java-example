package org.example;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.github.cdimascio.dotenv.Dotenv;

public class DotenvModule extends AbstractModule {
    @Provides
    @Singleton
    Dotenv provideDotenv() {
        return Dotenv.load();
    }
}
