package com.paprocksci.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {

    @Test
    void paperBeatsRock() {
        assertTrue(Hand.PAPER.beats(Hand.ROCK));
    }

    @Test
    void rockBeatsScissors() {
        assertTrue(Hand.ROCK.beats(Hand.SCISSORS));
    }

    @Test
    void scissorsBeatPaper() {
        assertTrue(Hand.SCISSORS.beats(Hand.PAPER));
    }

    @Test
    void rockBeatsLike() {
        assertTrue(Hand.ROCK.beats(Hand.LIKE));
    }

    @Test
    void paperBeatsLike() {
        assertTrue(Hand.PAPER.beats(Hand.LIKE));
    }

    @Test
    void scissorsBeatLike() {
        assertTrue(Hand.SCISSORS.beats(Hand.LIKE));
    }

    @Test
    void dislikeBeatsRockPaperAndScissors() {
        assertTrue(Hand.DISLIKE.beats(Hand.ROCK));
        assertTrue(Hand.DISLIKE.beats(Hand.PAPER));
        assertTrue(Hand.DISLIKE.beats(Hand.SCISSORS));
    }

    @Test
    void likeNeverBeatsAnotherHand() {
        for (Hand other : Hand.values()) {
            assertFalse(Hand.LIKE.beats(other));
        }
    }

    @Test
    void nothingBeatsDislike() {
        for (Hand other : Hand.values()) {
            assertFalse(other.beats(Hand.DISLIKE));
        }
    }

    @Test
    void likeAndDislikeAlwaysDraw() {
        assertTrue(Hand.LIKE.draws(Hand.DISLIKE));
        assertTrue(Hand.DISLIKE.draws(Hand.LIKE));
        assertFalse(Hand.LIKE.beats(Hand.DISLIKE));
        assertFalse(Hand.DISLIKE.beats(Hand.LIKE));
    }

    @ParameterizedTest
    @EnumSource(Hand.class)
    void identicalHandsDoNotBeatEachOther(Hand hand) {
        assertFalse(hand.beats(hand));
    }

    @ParameterizedTest
    @EnumSource(Hand.class)
    void identicalHandsDraw(Hand hand) {
        assertTrue(hand.draws(hand));
    }

    @Test
    void differentClassicHandsDoNotDraw() {
        assertFalse(Hand.ROCK.draws(Hand.PAPER));
        assertFalse(Hand.PAPER.draws(Hand.SCISSORS));
        assertFalse(Hand.SCISSORS.draws(Hand.ROCK));
    }
}
