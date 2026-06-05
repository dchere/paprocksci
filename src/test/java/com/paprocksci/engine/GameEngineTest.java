package com.paprocksci.engine;

import com.paprocksci.model.Hand;
import com.paprocksci.model.Winner;
import com.paprocksci.strategy.ComputerStrategy;
import com.paprocksci.ui.UserInterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameEngineTest {

    @Mock
    private UserInterface mockUi;

    @Mock
    private ComputerStrategy mockStrategy;

    private GameEngine gameEngine;

    @BeforeEach
    void setUp() {
        gameEngine = new GameEngine(mockUi, mockStrategy);
    }

    @Test
    void testEvaluateRound_PlayerWins() {
        assertEquals(Winner.PLAYER, gameEngine.evaluateRound(Hand.ROCK, Hand.SCISSORS));
        assertEquals(Winner.PLAYER, gameEngine.evaluateRound(Hand.PAPER, Hand.ROCK));
        assertEquals(Winner.PLAYER, gameEngine.evaluateRound(Hand.SCISSORS, Hand.PAPER));
    }

    @Test
    void testEvaluateRound_ComputerWins() {
        assertEquals(Winner.COMPUTER, gameEngine.evaluateRound(Hand.ROCK, Hand.PAPER));
        assertEquals(Winner.COMPUTER, gameEngine.evaluateRound(Hand.PAPER, Hand.SCISSORS));
        assertEquals(Winner.COMPUTER, gameEngine.evaluateRound(Hand.SCISSORS, Hand.ROCK));
    }

    @Test
    void testEvaluateRound_Draw() {
        assertEquals(Winner.DRAW, gameEngine.evaluateRound(Hand.ROCK, Hand.ROCK));
    }

    @Test
    void testGameLoop_UpdatesScoresCorrectly() {
        int totalRounds = 3;
        when(mockUi.askForNumberOfRounds()).thenReturn(totalRounds);

        when(mockUi.getPlayerHand(anyInt(), eq(totalRounds)))
                .thenReturn(Hand.ROCK)
                .thenReturn(Hand.PAPER)
                .thenReturn(Hand.SCISSORS);

        when(mockStrategy.generateHand())
                .thenReturn(Hand.SCISSORS) // Player wins
                .thenReturn(Hand.SCISSORS) // Computer wins
                .thenReturn(Hand.SCISSORS); // Draw

        // Act
        gameEngine.start();

        // Assert
        assertEquals(1, gameEngine.getWins(), "Player should have 1 win");
        assertEquals(1, gameEngine.getLosses(), "Player should have 1 loss");
        assertEquals(1, gameEngine.getDraws(), "Player should have 1 draw");

        verify(mockUi).displayFinalScore(1, 1, 1);

        verify(mockUi, times(totalRounds)).getPlayerHand(anyInt(), eq(totalRounds));
    }
}