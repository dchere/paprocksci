package com.paprocksci.ui;

import com.paprocksci.model.Hand;
import com.paprocksci.model.RoundResult;

public interface PlayerVsComputerPresenter {
    Hand askForHand(int currentRound, int totalRounds);

    void displayRoundResult(Hand player0Hand, Hand player1Hand, RoundResult result);

    void displayFinalScore(int player0Wins, int player1Wins, int draws);
}
