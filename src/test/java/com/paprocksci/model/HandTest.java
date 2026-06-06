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
    void likeBeatsDislike() {
        assertTrue(Hand.LIKE.beats(Hand.DISLIKE));
    }

    @Test
    void dislikeBeatsScissors() {
        assertTrue(Hand.DISLIKE.beats(Hand.SCISSORS));
    }

    @Test
    void likeBeatsPaper() {
        assertTrue(Hand.LIKE.beats(Hand.PAPER));
    }

    @Test
    void paperBeatsDislike() {
        assertTrue(Hand.PAPER.beats(Hand.DISLIKE));
    }

    @Test
    void scissorsBeatLike() {
        assertTrue(Hand.SCISSORS.beats(Hand.LIKE));
    }

    @Test
    void dislikeBeatsRock() {
        assertTrue(Hand.DISLIKE.beats(Hand.ROCK));
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
    void differentHandsDoNotDraw() {
        assertFalse(Hand.ROCK.draws(Hand.PAPER));
        assertFalse(Hand.LIKE.draws(Hand.DISLIKE));
    }
}
