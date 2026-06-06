package com.paprocksci;

import com.paprocksci.engine.GameEngine;
import com.paprocksci.strategy.ComputerStrategy;
import com.paprocksci.strategy.RandomHand;
import com.paprocksci.ui.ConsoleUserInterface;

public class Main {
    public static void main(String[] args) {
        ConsoleUserInterface ui = new ConsoleUserInterface();
        ComputerStrategy strategy = new RandomHand();

        GameEngine engine = new GameEngine(ui, strategy);
        engine.start();
    }
}
