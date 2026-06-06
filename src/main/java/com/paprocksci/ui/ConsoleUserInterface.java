package com.paprocksci.ui;

import com.paprocksci.model.GameMode;
import com.paprocksci.model.Hand;
import com.paprocksci.model.RoundResult;
import com.paprocksci.model.Team;

import java.util.Scanner;

public class ConsoleUserInterface implements GameUi {

    private final Scanner scanner;
    private final PlayerVsComputerPresenter playerVsComputerPresenter = new PlayerVsComputerConsolePresenter();

    public ConsoleUserInterface() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Hand askForHand(int currentRound, int totalRounds) {
        return playerVsComputerPresenter.askForHand(currentRound, totalRounds);
    }

    @Override
    public void displayRoundResult(Hand player0Hand, Hand player1Hand, RoundResult result) {
        playerVsComputerPresenter.displayRoundResult(player0Hand, player1Hand, result);
    }

    @Override
    public void displayFinalScore(int player0Wins, int player1Wins, int draws) {
        playerVsComputerPresenter.displayFinalScore(player0Wins, player1Wins, draws);
    }

    @Override
    public GameMode askForGameMode() {
        while (true) {
            System.out.print(AnsiColor.prompt(
                    "Select game mode ",
                    ConsoleInputParser.GAME_MODE_PROMPT_RULES + ": "));
            String input = scanner.nextLine();
            try {
                return ConsoleInputParser.parseGameMode(input);
            } catch (IllegalArgumentException e) {
                displayInvalidInput(e.getMessage());
            }
        }
    }

    @Override
    public int askForNumberOfRounds() {
        while (true) {
            System.out.print(AnsiColor.apply(AnsiColor.Style.PROMPT, "How many rounds would you like to play? "));
            String input = scanner.nextLine();
            try {
                return ConsoleInputParser.parseRounds(input);
            } catch (IllegalArgumentException e) {
                displayInvalidInput(e.getMessage());
            }
        }
    }

    @Override
    public Team askForFavoriteTeam() {
        while (true) {
            System.out.print(AnsiColor.prompt(
                    "Which team are you rooting for ",
                    ConsoleInputParser.TEAM_PROMPT_RULES + "? "));
            String input = scanner.nextLine();
            try {
                return ConsoleInputParser.parseTeam(input);
            } catch (IllegalArgumentException e) {
                displayInvalidInput(e.getMessage());
            }
        }
    }

    @Override
    public void displayFinalScore(int player0Wins, int player1Wins, int draws, Team favoriteTeam) {
        printScoreHeader();
        AnsiColor.printf(AnsiColor.Style.BLUE, "Blue wins:\t%d%n", player0Wins);
        AnsiColor.printf(AnsiColor.Style.YELLOW, "Yellow wins:\t%d%n", player1Wins);
        AnsiColor.printf(AnsiColor.Style.DRAW, "Draws:\t\t%d%n", draws);
        printScoreFooter();

        if (player0Wins == player1Wins) {
            AnsiColor.println(AnsiColor.Style.DRAW, "The match ended in a draw.");
            return;
        }

        Team winningTeam = player0Wins > player1Wins ? Team.BLUE : Team.YELLOW;
        if (winningTeam == favoriteTeam) {
            AnsiColor.println(teamStyle(favoriteTeam),
                    String.format("Your team (%s) won!", favoriteTeam.name().toLowerCase()));
        } else {
            AnsiColor.println(AnsiColor.Style.ERROR,
                    String.format("Your team (%s) lost.", favoriteTeam.name().toLowerCase()));
        }
    }

    @Override
    public void displayMessage(String message) {
        AnsiColor.println(AnsiColor.Style.INFO, message);
    }

    private Hand readHand(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return ConsoleInputParser.parseHand(input);
            } catch (IllegalArgumentException e) {
                displayInvalidInput(e.getMessage());
            }
        }
    }

    private void displayInvalidInput(String message) {
        AnsiColor.println(AnsiColor.Style.ERROR, "Invalid input: " + message);
    }

    private void printScoreHeader() {
        System.out.println();
        AnsiColor.println(AnsiColor.Style.HEADER, "=== FINAL SCORE ===");
    }

    private void printScoreFooter() {
        AnsiColor.println(AnsiColor.Style.HEADER, "===================");
    }

    private AnsiColor.Style teamStyle(Team team) {
        return team == Team.BLUE ? AnsiColor.Style.BLUE : AnsiColor.Style.YELLOW;
    }

    private final class PlayerVsComputerConsolePresenter implements PlayerVsComputerPresenter {

        @Override
        public Hand askForHand(int currentRound, int totalRounds) {
            return readHand(String.format("%s%s %s: ",
                    AnsiColor.apply(AnsiColor.Style.INFO, String.format("\n[Round %d of %d]", currentRound, totalRounds)),
                    AnsiColor.apply(AnsiColor.Style.PROMPT, "Enter move"),
                    AnsiColor.apply(AnsiColor.Style.HINT, ConsoleInputParser.HAND_PROMPT_RULES)));
        }

        @Override
        public void displayRoundResult(Hand player0Hand, Hand player1Hand, RoundResult result) {
            HandAsciiArt.printDuel(
                    player0Hand,
                    player1Hand,
                    "You",
                    "Computer",
                    AnsiColor.Style.PLAYER,
                    AnsiColor.Style.COMPUTER);

            switch (result) {
                case PLAYER_0 -> AnsiColor.println(AnsiColor.Style.SUCCESS, "Result: You win this round!");
                case PLAYER_1 -> AnsiColor.println(AnsiColor.Style.COMPUTER, "Result: Computer wins this round.");
                case DRAW -> AnsiColor.println(AnsiColor.Style.DRAW, "Result: It's a draw!");
            }
        }

        @Override
        public void displayFinalScore(int player0Wins, int player1Wins, int draws) {
            printScoreHeader();
            AnsiColor.printf(AnsiColor.Style.SUCCESS, "Wins:\t%d%n", player0Wins);
            AnsiColor.printf(AnsiColor.Style.COMPUTER, "Losses:\t%d%n", player1Wins);
            AnsiColor.printf(AnsiColor.Style.DRAW, "Draws:\t%d%n", draws);
            printScoreFooter();

            if (player0Wins > player1Wins) {
                AnsiColor.println(AnsiColor.Style.SUCCESS, "Congratulations! You won the game!");
            } else if (player1Wins > player0Wins) {
                AnsiColor.println(AnsiColor.Style.COMPUTER, "Game Over! The computer won the game.");
            } else {
                AnsiColor.println(AnsiColor.Style.DRAW, "The overall game ended in a draw.");
            }
        }
    }
}
