package com.paprocksci.engine;

import com.paprocksci.model.Hand;
import com.paprocksci.model.Winner;
import com.paprocksci.strategy.ComputerStrategy;
import com.paprocksci.ui.UserInterface;

public class GameEngine {
    private final UserInterface ui;
    private final ComputerStrategy computerStrategy;

    private int wins = 0;
    private int losses = 0;
    private int draws = 0;

    public GameEngine(UserInterface ui, ComputerStrategy computerStrategy) {
        this.ui = ui;
        this.computerStrategy = computerStrategy;
    }

    public void start() {
        ui.displayMessage("Welcome to the Paper-Rock-Scissors game!");
        int totalRounds = ui.askForNumberOfRounds();

        for (int i = 1; i <= totalRounds; i++) {
            playRound(i, totalRounds);
        }

        ui.displayFinalScore(wins, losses, draws);
    }

    private void playRound(int currentRound, int totalRounds) {
        Hand playerMove = ui.getPlayerHand(currentRound, totalRounds);
        Hand computerMove = computerStrategy.generateHand();

        Winner result = evaluateRound(playerMove, computerMove);
        updateScore(result);

        ui.displayRoundResult(playerMove, computerMove, result);
    }

    protected Winner evaluateRound(Hand playerMove, Hand computerMove) {
        if (playerMove.draws(computerMove)) {
            return Winner.DRAW;
        } else if (playerMove.beats(computerMove)) {
            return Winner.PLAYER;
        } else {
            return Winner.COMPUTER;
        }
    }

    private void updateScore(Winner result) {
        switch (result) {
            case PLAYER -> wins++;
            case COMPUTER -> losses++;
            case DRAW -> draws++;
        }
    }

    // Getters for testing purposes
    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }
}