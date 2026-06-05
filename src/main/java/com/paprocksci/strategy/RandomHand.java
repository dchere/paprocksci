package com.paprocksci.strategy;

import com.paprocksci.model.Hand;
import java.util.Random;

public class RandomHand implements ComputerStrategy {
    private final Random random;
    private final Hand[] hands;

    public RandomHand() {
        this.random = new Random();
        this.hands = Hand.values();
    }

    @Override
    public Hand generateHand() {
        return hands[random.nextInt(hands.length)];
    }

}