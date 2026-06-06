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
        if (this == other) {
            return true;
        }
        return isLikeDislikePair(this, other);
    }

    /**
     * Returns true if this hand beats the other hand.
     */
    public boolean beats(Hand other) {
        if (draws(other)) {
            return false;
        }
        return switch (this) {
            case ROCK -> other == SCISSORS || other == LIKE;
            case PAPER -> other == ROCK || other == LIKE;
            case SCISSORS -> other == PAPER || other == LIKE;
            case LIKE -> false;
            case DISLIKE -> other == ROCK || other == PAPER || other == SCISSORS;
        };
    }

    private static boolean isLikeDislikePair(Hand first, Hand second) {
        return first == LIKE && second == DISLIKE || first == DISLIKE && second == LIKE;
    }
}
