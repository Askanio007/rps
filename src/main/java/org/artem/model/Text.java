package org.artem.model;

import java.util.Arrays;
import java.util.List;

public interface Text {

    String WELCOME = "Welcome! ";
    String ENTER_NAME = "Enter you name: ";
    String NAME_SAVED = "Hello, %s! ";
    String WAITING_START_GAME = "Wait competitor... ";
    String START_GAME = "Game started! Rock(R), paper(P), scissors(S), 1, 2, 3: ";
    String ILLEGAL_CHOOSE = "You should enter R(rock), S(scissors) or P(paper): ";
    String WAITING_DECISION_PLAYER = "You choice accepted! Wait another player. ";
    String GAME_OVER_LOSS = "Finish! You lose! ";
    String GAME_OVER_WIN = "Finish! You win! ";
    String GAME_OVER = "Finish! Dead heat! One more time: ";
    String PLAYER_LEAVE = "Competitor leave. You win! ";

    String PAPER = "p";
    String ROCK = "r";
    String SCISSORS = "s";

    List<String> GAME_ELEMENTS = Arrays.asList(PAPER, ROCK, SCISSORS);
}
