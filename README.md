# PapRockSci — Paper-Rock-Scissors

Java console application for the classic **Rock-Paper-Scissors** game.

## Overview

A console-based Paper–Rock–Scissors game with two modes:

1.**Player vs. Computer** – You enter each move, and the result is shown after every round.
2.**Computer vs. Computer** – Two computer-controlled teams, Blue and Yellow, play silently. You choose which team you are a fan of, and only the final score is shown.

In both modes, you can choose how many rounds to play. The console interface uses color-coded output to distinguish prompts, hints, errors, teams, and results.

All computer players use an honest random strategy: each round they pick Paper, Rock, or Scissors with equal probability, with no memory of previous moves.

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
   java -jar target/paper-rock-scissors-1.0.0-SNAPSHOT.jar
   ```

**Run with colors forced on (e.g. in some IDE terminals):**

   ```bash
   FORCE_COLOR=true java -jar target/paper-rock-scissors-1.0.0-SNAPSHOT.jar
   ```
