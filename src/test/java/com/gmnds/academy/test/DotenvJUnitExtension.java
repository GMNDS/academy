package com.gmnds.academy.test;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import io.github.cdimascio.dotenv.DotenvEntry;
import java.util.Set;

/**
 * JUnit 5 extension which loads environment variables from .env.test (or .env) and sets them
 * as system properties so Spring can read them in tests.
 *
 * This extension will be automatically loaded by JUnit via ServiceLoader (see resources entry).
 */
public class DotenvJUnitExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .filename(".env.test")
                .load();

        // Fallback to .env if .env.test is not present
        if (dotenv.entries().isEmpty()) {
            dotenv = Dotenv.configure()
                    .ignoreIfMissing()
                    .filename(".env")
                    .load();
        }

        Set<DotenvEntry> entries = dotenv.entries();
        for (DotenvEntry entry : entries) {
            // Only set system property if not already set, to allow CI overrides
            if (System.getProperty(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue());
            }
        }
    }
}
