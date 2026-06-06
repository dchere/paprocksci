package com.paprocksci.ui;

import com.paprocksci.model.GameMode;
import com.paprocksci.model.Hand;
import com.paprocksci.model.Team;

public class ConsoleInputParser {

    public static final String HAND_PROMPT_RULES =
            "[1=Paper (p), 2=Rock (r), 3=Scissors (s), 4=Like (l), 5=Dislike (i)]";
    public static final String GAME_MODE_PROMPT_RULES = "[1=Play vs Computer, 2=Computer vs Computer]";
    public static final String TEAM_PROMPT_RULES = "[b=Blue, y=Yellow]";

    private static String requireNonBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }
        return input.trim();
    }

    public static Hand parseHand(String input) {
        return switch (requireNonBlank(input).toLowerCase()) {
            case "1", "p", "paper" -> Hand.PAPER;
            case "2", "r", "rock" -> Hand.ROCK;
            case "3", "s", "scissors" -> Hand.SCISSORS;
            case "4", "l", "like" -> Hand.LIKE;
            case "5", "i", "dislike" -> Hand.DISLIKE;
            default -> throw new IllegalArgumentException("Invalid move input: " + input);
        };
    }

    public static GameMode parseGameMode(String input) {
        return switch (requireNonBlank(input).toLowerCase()) {
            case "1", "p", "player", "pvc" -> GameMode.PLAYER_VS_COMPUTER;
            case "2", "c", "computer", "cvc" -> GameMode.COMPUTER_VS_COMPUTER;
            default -> throw new IllegalArgumentException("Invalid game mode: " + input);
        };
    }

    public static Team parseTeam(String input) {
        return switch (requireNonBlank(input).toLowerCase()) {
            case "b", "blue" -> Team.BLUE;
            case "y", "yellow" -> Team.YELLOW;
            default -> throw new IllegalArgumentException("Invalid team: " + input);
        };
    }

    public static int parseRounds(String input) {
        try {
            int rounds = Integer.parseInt(requireNonBlank(input));
            if (rounds <= 0) {
                throw new IllegalArgumentException("Number of rounds must be a positive integer.");
            }
            return rounds;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format.");
        }
    }
}