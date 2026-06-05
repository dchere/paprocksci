package com.paprocksci.ui;

import com.paprocksci.model.Hand;
import com.paprocksci.model.Winner;

public interface UserInterface {
    int askForNumberOfRounds();

    Hand getPlayerHand(int currentRound, int totalRounds);

    void displayRoundResult(Hand playerHand, Hand computerHand, Winner result);

    void displayFinalScore(int wins, int losses, int draws);

    void displayMessage(String message);
}