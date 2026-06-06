package com.paprocksci.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnsiColorTest {

    @AfterEach
    void tearDown() {
        AnsiColor.resetEnabledForTesting();
    }

    @Test
    void applyReturnsPlainTextWhenColorsDisabled() {
        AnsiColor.setEnabledForTesting(false);

        assertEquals("hello", AnsiColor.apply(AnsiColor.Style.ERROR, "hello"));
    }

    @Test
    void applyWrapsTextWhenColorsEnabled() {
        AnsiColor.setEnabledForTesting(true);

        String colored = AnsiColor.apply(AnsiColor.Style.ERROR, "hello");

        assertTrue(colored.contains("hello"));
        assertTrue(colored.contains("\033[31m"));
        assertTrue(colored.contains("\033[0m"));
    }

    @Test
    void promptColorsQuestionAndHintSeparately() {
        AnsiColor.setEnabledForTesting(true);

        String prompt = AnsiColor.prompt("Choose ", "[1=One, 2=Two]");

        assertTrue(prompt.contains("Choose "));
        assertTrue(prompt.contains("[1=One, 2=Two]"));
        assertTrue(prompt.contains("\033[36m"));
        assertTrue(prompt.contains("\033[90m"));
    }

    @Test
    void applyReturnsNullWhenInputIsNull() {
        AnsiColor.setEnabledForTesting(true);

        assertEquals(null, AnsiColor.apply(AnsiColor.Style.INFO, null));
    }

    @Test
    void disabledPromptDoesNotContainEscapeCodes() {
        AnsiColor.setEnabledForTesting(false);

        String prompt = AnsiColor.prompt("Choose ", "[hint]");

        assertFalse(prompt.contains("\033["));
        assertEquals("Choose [hint]", prompt);
    }
}
