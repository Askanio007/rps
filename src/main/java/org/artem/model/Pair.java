package org.artem.model;

public class Pair {
    private Player one;
    private Player two;

    public static Pair of(Player one, Player two) {
        Pair pair = new Pair();
        pair.setOne(one);
        pair.setTwo(two);
        return pair;
    }

    public Player getOne() {
        return one;
    }

    public void setOne(Player one) {
        this.one = one;
    }

    public Player getTwo() {
        return two;
    }

    public void setTwo(Player two) {
        this.two = two;
    }
}
