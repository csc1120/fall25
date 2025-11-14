/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week11;

public class Schedule {
    String day;

    public Schedule(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Today is " + day;
    }
}
