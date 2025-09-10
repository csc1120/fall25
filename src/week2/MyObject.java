/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week2;

import java.io.Serializable;

public class MyObject implements Serializable {
    private final int myInt;
    private final double double1;
    private final double double2;
    private final String word;

    public MyObject(int myInt, double double1, double double2, String word) {
        this.myInt = myInt;
        this.double1 = double1;
        this.double2 = double2;
        this.word = word;
    }

    public int getMyInt() {
        return myInt;
    }

    public double getDouble1() {
        return double1;
    }

    public double getDouble2() {
        return double2;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        return word + " " + myInt + " " + double1 + " " + double2;
    }
}
