package com.paprocksci.ui;

import com.paprocksci.model.GameMode;

public interface GameSetupUi {
    GameMode askForGameMode();

    int askForNumberOfRounds();

    void displayMessage(String message);
}
