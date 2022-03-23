package SnakeLadderGame.models;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    private final int minVal = 1;
    private final int maxVal = 6;
    private int currentVal;

    public int getCurrentVal() {
        return currentVal;
    }

    public int roll() {
        currentVal = ThreadLocalRandom.current().nextInt(minVal, maxVal+1);
        System.out.println("Dice rolled, value is " + currentVal);
        return currentVal;
    }
}
