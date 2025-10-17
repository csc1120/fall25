/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week7;

import java.util.Random;

public class Die {
    private final int numSides;
    private int currentValue;
    private final Random generator;

    public Die(int numSides) {
        final int maxSides = 100;
        final int minSides = 2;
        final int basicDie = 6;
        if(numSides < minSides || numSides > maxSides) {
            this.numSides = basicDie;
        } else {
            this.numSides = numSides;
        }
        this.currentValue = 0;
        this.generator = new Random();
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void roll() {
        currentValue = generator.nextInt(numSides) + 1;
    }
}
