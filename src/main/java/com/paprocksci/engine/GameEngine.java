package com.paprocksci.engine;

import com.paprocksci.model.GameMode;
import com.paprocksci.model.Hand;
import com.paprocksci.model.RoundResult;
import com.paprocksci.model.Team;
import com.paprocksci.strategy.ComputerStrategy;
import com.paprocksci.ui.GameUi;

public class GameEngine {
    private final GameUi ui;
    private final ComputerStrategy computerStrategy;

    private int player0Wins = 0;
    private int player1Wins = 0;
    private int draws = 0;

    public GameEngine(GameUi ui, ComputerStrategy computerStrategy) {
        this.ui = ui;
        this.computerStrategy = computerStrategy;
    }

    public void start() {
        ui.displayMessage("Welcome to the Paper-Rock-Scissors game!");
        GameMode mode = ui.askForGameMode();
        int totalRounds = ui.askForNumberOfRounds();

        if (mode == GameMode.COMPUTER_VS_COMPUTER) {
            Team favoriteTeam = ui.askForFavoriteTeam();
            startComputerVsComputer(totalRounds, favoriteTeam);
        } else {
            startPlayerVsComputer(totalRounds);
        }
    }

    private void startPlayerVsComputer(int totalRounds) {
        resetScore();
        for (int i = 1; i <= totalRounds; i++) {
            playPlayerVsComputerRound(i, totalRounds);
        }

        ui.displayFinalScore(player0Wins, player1Wins, draws);
    }

    private void startComputerVsComputer(int totalRounds, Team favoriteTeam) {
        int player0Wins = 0;
        int player1Wins = 0;
        int teamDraws = 0;

        for (int i = 0; i < totalRounds; i++) {
            Hand player0Move = computerStrategy.generateHand();
            Hand player1Move = computerStrategy.generateHand();

            RoundResult result = evaluateRound(player0Move, player1Move);
            switch (result) {
                case PLAYER_0 -> player0Wins++;
                case PLAYER_1 -> player1Wins++;
                case DRAW -> teamDraws++;
            }
        }

        ui.displayFinalScore(player0Wins, player1Wins, teamDraws, favoriteTeam);
    }

    private void playPlayerVsComputerRound(int currentRound, int totalRounds) {
        Hand player0Move = ui.askForHand(currentRound, totalRounds);
        Hand player1Move = computerStrategy.generateHand();

        RoundResult result = evaluateRound(player0Move, player1Move);
        updateScore(result);

        ui.displayRoundResult(player0Move, player1Move, result);
    }

    protected RoundResult evaluateRound(Hand player0Move, Hand player1Move) {
        if (player0Move.draws(player1Move)) {
            return RoundResult.DRAW;
        } else if (player0Move.beats(player1Move)) {
            return RoundResult.PLAYER_0;
        } else {
            return RoundResult.PLAYER_1;
        }
    }

    private void updateScore(RoundResult result) {
        switch (result) {
            case PLAYER_0 -> player0Wins++;
            case PLAYER_1 -> player1Wins++;
            case DRAW -> draws++;
        }
    }

    private void resetScore() {
        player0Wins = 0;
        player1Wins = 0;
        draws = 0;
    }

    // Getters for testing purposes
    public int getPlayer0Wins() {
        return player0Wins;
    }

    public int getPlayer1Wins() {
        return player1Wins;
    }

    public int getDraws() {
        return draws;
    }
}
