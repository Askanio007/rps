package org.artem.model;

import java.util.stream.Stream;

public enum GameElement {

    ROCK(Text.ROCK, "SCISSORS"),
    SCISSORS(Text.SCISSORS,  "PAPER"),
    PAPER(Text.PAPER, "ROCK");

    private final String win;
    private final String value;

    GameElement(String value, String win) {
        this.value = value;
        this.win = win;
    }

    public int compare(GameElement e) {
        if (e == this) {
            return 0;
        }

        return GameElement.valueOf(win) == e ? 1 : -1;
    }

    public String getValue() {
        return value;
    }

    public static GameElement getByValue(String str) {
        return Stream.of(GameElement.values())
                .filter((e) -> e.getValue().equals(str))
                .findFirst()
                .orElse(null);
    }
}

