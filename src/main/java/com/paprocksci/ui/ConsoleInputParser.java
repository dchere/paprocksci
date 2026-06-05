package com.paprocksci.ui;

import com.paprocksci.model.Hand;

public class ConsoleInputParser {

    public static final String HAND_PROMPT_RULES = "[1=Paper (p), 2=Rock (r), 3=Scissors (s)]";

    /**
     * Parses a string input into a Hand.
     */
    public static Hand parseHand(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }

        return switch (input.trim().toLowerCase()) {
            case "1", "p", "paper" -> Hand.PAPER;
            case "2", "r", "rock" -> Hand.ROCK;
            case "3", "s", "scissors" -> Hand.SCISSORS;
            default -> throw new IllegalArgumentException("Invalid move input: " + input);
        };
    }

    /**
     * Parses the user's string input into a valid number of rounds.
     */
    public static int parseRounds(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }

        try {
            int rounds = Integer.parseInt(input.trim());
            if (rounds <= 0) {
                throw new IllegalArgumentException("Number of rounds must be a positive integer.");
            }
            return rounds;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format.");
        }
    }
}