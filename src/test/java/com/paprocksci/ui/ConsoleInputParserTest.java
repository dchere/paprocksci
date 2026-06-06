package com.paprocksci.ui;

import com.paprocksci.model.GameMode;
import com.paprocksci.model.Hand;
import com.paprocksci.model.Team;
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
    @ValueSource(strings = { "4", "l", "L", "like", "LIKE", "  l  " })
    void testParseHand_Like(String input) {
        assertEquals(Hand.LIKE, ConsoleInputParser.parseHand(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "5", "i", "I", "dislike", "DISLIKE", "  i  " })
    void testParseHand_Dislike(String input) {
        assertEquals(Hand.DISLIKE, ConsoleInputParser.parseHand(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "d", "diss", "k", "lizard", "spock", "42", "x", "random" })
    void testParseHand_InvalidInputThrowsException(String input) {
        assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseHand(input));
    }

    @Test
    void testParseHand_NullOrEmptyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseHand(null));
        assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseHand(""));
        assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseHand("   "));
    }

    // --- Game Mode Parsing Tests ---

    @ParameterizedTest
    @ValueSource(strings = { "1", "p", "player", "PLAYER", "  p  " })
    void testParseGameMode_PlayerVsComputer(String input) {
        assertEquals(GameMode.PLAYER_VS_COMPUTER, ConsoleInputParser.parseGameMode(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "2", "c", "computer", "cvc", "COMPUTER", "  c  " })
    void testParseGameMode_ComputerVsComputer(String input) {
        assertEquals(GameMode.COMPUTER_VS_COMPUTER, ConsoleInputParser.parseGameMode(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "0", "spectator", "blue", "pvp", "2p" })
    void testParseGameMode_InvalidInputThrowsException(String input) {
        assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseGameMode(input));
    }

    // --- Team Parsing Tests ---

    @ParameterizedTest
    @ValueSource(strings = { "b", "B", "blue", "BLUE", "  blue  " })
    void testParseTeam_Blue(String input) {
        assertEquals(Team.BLUE, ConsoleInputParser.parseTeam(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "y", "Y", "yellow", "YELLOW", "  yellow  " })
    void testParseTeam_Yellow(String input) {
        assertEquals(Team.YELLOW, ConsoleInputParser.parseTeam(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "red", "1", "green" })
    void testParseTeam_InvalidInputThrowsException(String input) {
        assertThrows(IllegalArgumentException.class, () -> ConsoleInputParser.parseTeam(input));
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