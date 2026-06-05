package com.paprocksci.model;

public enum Hand {
    ROCK, // showing fist
    PAPER, // showing open hand
    SCISSORS; // showing index and middle finger

    /**
     * Determines if this hand draws with the other hand.
     * It was implemented to stay in boundaries of "Tell, Don't Ask" principle, so
     * the logic could be keept in one place.
     *
     * @param other The opponent's hand.
     * @return true if this hand draws with the other hand, false otherwise.
     */
    public boolean draws(Hand other) {
        return this == other;
    }

    /**
     * Implementation of the schema to select the winner.
     *
     * @param other The opponent's hand.
     * @return true if this hand beats the other hand, false otherwise.
     */
    public boolean beats(Hand other) {

        return (this == PAPER && other == ROCK) || // Paper beats (wraps) rock
                (this == ROCK && other == SCISSORS) || // Rock beats (blunts) scissors
                (this == SCISSORS && other == PAPER); // Scissors beats (cuts) paper
    }
}