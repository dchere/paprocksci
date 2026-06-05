package com.paprocksci.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {

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
    void identicalHandsDoNotBeatEachOther() {
        assertFalse(Hand.ROCK.beats(Hand.ROCK));
        assertFalse(Hand.PAPER.beats(Hand.PAPER));
        assertFalse(Hand.SCISSORS.beats(Hand.SCISSORS));
    }

    @Test
    void handsDoNotBeatTheirDefeaters() {
        assertFalse(Hand.ROCK.beats(Hand.PAPER));
        assertFalse(Hand.PAPER.beats(Hand.SCISSORS));
        assertFalse(Hand.SCISSORS.beats(Hand.ROCK));
    }

    @Test
    void identicalHandsDraw() {
        assertTrue(Hand.ROCK.draws(Hand.ROCK));
        assertTrue(Hand.PAPER.draws(Hand.PAPER));
        assertTrue(Hand.SCISSORS.draws(Hand.SCISSORS));
    }

    @Test
    void differentHandsDoNotDraw() {
        assertFalse(Hand.ROCK.draws(Hand.PAPER));
        assertFalse(Hand.ROCK.draws(Hand.SCISSORS));
        assertFalse(Hand.PAPER.draws(Hand.ROCK));
        assertFalse(Hand.PAPER.draws(Hand.SCISSORS));
        assertFalse(Hand.SCISSORS.draws(Hand.ROCK));
        assertFalse(Hand.SCISSORS.draws(Hand.PAPER));
    }
}