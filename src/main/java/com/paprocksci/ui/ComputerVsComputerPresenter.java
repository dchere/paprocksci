package com.paprocksci.ui;

import com.paprocksci.model.Team;

public interface ComputerVsComputerPresenter {
    Team askForFavoriteTeam();

    void displayFinalScore(int player0Wins, int player1Wins, int draws, Team favoriteTeam);
}
