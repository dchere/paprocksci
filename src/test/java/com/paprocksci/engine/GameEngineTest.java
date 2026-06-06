package com.paprocksci.engine;

import com.paprocksci.model.GameMode;
import com.paprocksci.model.Hand;
import com.paprocksci.model.RoundResult;
import com.paprocksci.model.Team;
import com.paprocksci.strategy.ComputerStrategy;
import com.paprocksci.ui.GameUi;

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
    private GameUi mockUi;

    @Mock
    private ComputerStrategy mockStrategy;

    private GameEngine gameEngine;

    @BeforeEach
    void setUp() {
        gameEngine = new GameEngine(mockUi, mockStrategy);
    }

    @Test
    void testEvaluateRound_Player0Wins() {
        assertEquals(RoundResult.PLAYER_0, gameEngine.evaluateRound(Hand.ROCK, Hand.SCISSORS));
        assertEquals(RoundResult.PLAYER_0, gameEngine.evaluateRound(Hand.PAPER, Hand.ROCK));
        assertEquals(RoundResult.PLAYER_0, gameEngine.evaluateRound(Hand.SCISSORS, Hand.PAPER));
    }

    @Test
    void testEvaluateRound_Player1Wins() {
        assertEquals(RoundResult.PLAYER_1, gameEngine.evaluateRound(Hand.ROCK, Hand.PAPER));
        assertEquals(RoundResult.PLAYER_1, gameEngine.evaluateRound(Hand.PAPER, Hand.SCISSORS));
        assertEquals(RoundResult.PLAYER_1, gameEngine.evaluateRound(Hand.SCISSORS, Hand.ROCK));
    }

    @Test
    void testEvaluateRound_Draw() {
        assertEquals(RoundResult.DRAW, gameEngine.evaluateRound(Hand.ROCK, Hand.ROCK));
    }

    @Test
    void testPlayerVsComputer_UpdatesScoresCorrectly() {
        int totalRounds = 3;
        when(mockUi.askForGameMode()).thenReturn(GameMode.PLAYER_VS_COMPUTER);
        when(mockUi.askForNumberOfRounds()).thenReturn(totalRounds);

        when(mockUi.askForHand(anyInt(), eq(totalRounds)))
                .thenReturn(Hand.ROCK)
                .thenReturn(Hand.PAPER)
                .thenReturn(Hand.SCISSORS);

        when(mockStrategy.generateHand())
                .thenReturn(Hand.SCISSORS)
                .thenReturn(Hand.SCISSORS)
                .thenReturn(Hand.SCISSORS);

        gameEngine.start();

        assertEquals(1, gameEngine.getPlayer0Wins());
        assertEquals(1, gameEngine.getPlayer1Wins());
        assertEquals(1, gameEngine.getDraws());

        verify(mockUi).displayFinalScore(1, 1, 1);
        verify(mockUi, times(totalRounds)).askForHand(anyInt(), eq(totalRounds));
    }

    @Test
    void testComputerVsComputer_ShowsOnlyFinalScore() {
        int totalRounds = 3;
        when(mockUi.askForGameMode()).thenReturn(GameMode.COMPUTER_VS_COMPUTER);
        when(mockUi.askForNumberOfRounds()).thenReturn(totalRounds);
        when(mockUi.askForFavoriteTeam()).thenReturn(Team.BLUE);

        when(mockStrategy.generateHand())
                .thenReturn(
                        Hand.ROCK, Hand.SCISSORS,
                        Hand.PAPER, Hand.SCISSORS,
                        Hand.SCISSORS, Hand.SCISSORS);

        gameEngine.start();

        verify(mockUi).displayFinalScore(1, 1, 1, Team.BLUE);
        verify(mockUi, never()).displayRoundResult(any(), any(), any());
    }
}
