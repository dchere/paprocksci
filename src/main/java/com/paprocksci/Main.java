package com.paprocksci;

import com.paprocksci.engine.GameEngine;
import com.paprocksci.strategy.ComputerStrategy;
import com.paprocksci.strategy.RandomHand;
import com.paprocksci.ui.ConsoleUserInterface;
import com.paprocksci.ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new ConsoleUserInterface();
        ComputerStrategy strategy = new RandomHand();

        GameEngine engine = new GameEngine(ui, strategy);
        engine.start();
    }
}