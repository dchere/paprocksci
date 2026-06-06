package com.paprocksci.model;

public enum Hand {
    ROCK,
    PAPER,
    SCISSORS,
    LIKE,
    DISLIKE;

    /**
     * Determines if this hand draws with the other hand.
     */
    public boolean draws(Hand other) {
        return this == other;
    }

    /**
     * Returns true if this hand beats the other hand.
     */
    public boolean beats(Hand other) {
        return switch (this) {
            case ROCK -> other == SCISSORS || other == LIKE;
            case PAPER -> other == ROCK || other == DISLIKE;
            case SCISSORS -> other == PAPER || other == LIKE;
            case LIKE -> other == PAPER || other == DISLIKE;
            case DISLIKE -> other == SCISSORS || other == ROCK;
        };
    }
}
