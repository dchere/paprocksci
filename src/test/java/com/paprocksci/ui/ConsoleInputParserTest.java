package com.paprocksci.ui;

import com.paprocksci.model.Hand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputParserTest {

    // --- Hand Parsing Tests ---

    @ParameterizedTest
    @ValueSource(strings = { "1", "p", "P", "paper", "PAPER", "  p  " })
    void testParseHand_Paper(String input) {
        assertEquals(Hand.PAPER, ConsoleInputParser.parseHand(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "2", "r", "R", "rock", "ROCK", " rOcK " })
    void testParseHand_Rock(String input) {
        assertEquals(Hand.ROCK, ConsoleInputParser.parseHand(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "3", "s", "S", "scissors", "SCISSORS", "ScIsSoRs" })
    void testParseHand_Scissors(String input) {
        assertEquals(Hand.SCISSORS, ConsoleInputParser.parseHand(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "42", "x", "lizard", "spock", "random" })
    void testParseHand_InvalidInputThrowsException(String input) {
        assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseHand(input));
    }

    @Test
    void testParseHand_NullOrEmptyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseHand(null));
        assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseHand(""));
        assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseHand("   "));
    }

    // --- Rounds Parsing Tests ---

    @ParameterizedTest
    @ValueSource(strings = { "1", "5", "100", " 10 " })
    void testParseRounds_ValidPositiveIntegers(String input) {
        assertTrue(ConsoleInputParser.parseRounds(input) > 0);
    }

    @ParameterizedTest
    @ValueSource(strings = { "0", "-1", "-50" })
    void testParseRounds_ZeroOrNegativeThrowsException(String input) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseRounds(input));
        assertEquals("Number of rounds must be a positive integer.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "five", "3.5", "10x" })
    void testParseRounds_NonIntegersThrowException(String input) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseRounds(input));
        assertEquals("Invalid number format.", exception.getMessage());
    }

    @Test
    void testParseRounds_NullOrEmptyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseRounds(null));
        assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseRounds("   "));
    }
}