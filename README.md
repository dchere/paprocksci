# PapRockSci — Rock-Paper-Scissors+ console game

Java console application for **Rock Paper Scissors Like Dislike**.

## Overview

A console-based Rock Paper Scissors (plus Like and Dislike) game with two modes:

1. **Player vs. Computer** – You enter each move, and the result is shown after every round.
2. **Computer vs. Computer** – Two computer-controlled teams, Blue and Yellow, play silently. You choose which team you are a fan of, and only the final score is shown.

In both modes, you can choose how many rounds to play. The console interface uses color-coded output to distinguish prompts, hints, errors, teams, and results.

All computer players use an honest random strategy: each round they pick one of five moves (Rock, Paper, Scissors, Like, Dislike) with equal probability, with no memory of previous moves.

## Rules

This is **not** classic Rock-Paper-Scissors. Five hands use a layered ruleset:

```
                DISLIKE  ← the strongest
                   ↓
       ________________________
      ╱            │           ╲
     ↓             ↓            ↓
    ROCK  --►  SCISSORS  --►  PAPER  --►  ROCK (classical cycle)
      ╲            │           ╱
       ╲           ↓          ╱
        \____________________/
                   ↓
                  LIKE  ← the weakest

       LIKE  ←——— draw ———→  DISLIKE
```

Read an arrow as **“beats”**. Like and Dislike always tie each other.

### Who beats whom

| Hand | Beats | Loses to | Draws with |
|------|-------|----------|------------|
| **Rock** | Scissors, Like | Paper, Dislike | Rock |
| **Paper** | Rock, Like | Scissors, Dislike | Paper |
| **Scissors** | Paper, Like | Rock, Dislike | Scissors |
| **Like** | — | Rock, Paper, Scissors | Like, Dislike |
| **Dislike** | Rock, Paper, Scissors | — | Dislike, Like |

## Console Colors

Output is styled with ANSI colors when running in a terminal. Colors are enabled automatically in interactive terminals. Override with environment variables:

- `FORCE_COLOR=true` — always use colors
- `NO_COLOR=1` — disable colors

## Prerequisites

*Java 25* or higher and *Maven* installed.

## Testing Libraries

*JUnit 5 (Jupiter)* and *Mockito*

## How to Build and Run

**Compile and Run Tests:**

   ```bash
   mvn clean test
   ```

**Package the Application:**

   ```bash
   mvn clean package
   ```

**Run the Game:**

   ```bash
   java -jar target/paper-rock-scissors-1.1.0.jar
   ```

**Run with colors forced on (e.g. in some IDE terminals):**

   ```bash
   FORCE_COLOR=true java -jar target/paper-rock-scissors-1.1.0.jar
   ```
