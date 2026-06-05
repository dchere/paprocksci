package com.paprocksci.ui;

import com.paprocksci.model.Hand;
import com.paprocksci.model.Winner;

import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface {

    private final Scanner scanner;

    public ConsoleUserInterface() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int askForNumberOfRounds() {
        while (true) {
            System.out.print("How many rounds would you like to play? ");
            String input = scanner.nextLine();
            try {
                return ConsoleInputParser.parseRounds(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    @Override
    public Hand getPlayerHand(int currentRound, int totalRounds) {
        while (true) {
            System.out.printf("\n[Round %d of %d] Enter move %s: ",
                    currentRound,
                    totalRounds,
                    ConsoleInputParser.HAND_PROMPT_RULES);
            String input = scanner.nextLine();
            try {
                return ConsoleInputParser.parseHand(input);
            } catch (IllegalArgumentException e) {
                System.out.printf("Invalid input: %s%n", e.getMessage());
            }
        }
    }

    @Override
    public void displayRoundResult(Hand playerHand, Hand computerHand, Winner result) {
        System.out.printf("You played: %s | Computer played: %s%n", playerHand, computerHand);
        switch (result) {
            case PLAYER -> System.out.println("Result: You win this round!");
            case COMPUTER -> System.out.println("Result: Computer wins this round.");
            case DRAW -> System.out.println("Result: It's a draw!");
        }
    }

    @Override
    public void displayFinalScore(int wins, int losses, int draws) {
        System.out.println("\n=== FINAL SCORE ===");
        System.out.printf("Wins:\t%d%n", wins);
        System.out.printf("Losses:\t%d%n", losses);
        System.out.printf("Draws:\t%d%n", draws);
        System.out.println("===================");

        if (wins > losses) {
            System.out.println("Congratulations! You won the game!");
        } else if (losses > wins) {
            System.out.println("Game Over! The computer won the game.");
        } else {
            System.out.println("The overall game ended in a draw.");
        }
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}