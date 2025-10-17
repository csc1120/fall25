/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week7;

public class Mugwump {
    static int id;
    private Die d6;
    private Die d10;
    private Die d20;
    private Die d100;
    private int maxHitPoints;
    private int hitPoints;

    public Mugwump() {
        this.d6 = new Die(6);
        this.d10 = new Die(10);
        this.d20 = new Die(20);
        this.d100 = new Die(100);
//        this.d6 = this.d10;
        this.hitPoints = setInitialHitPoints();
        this.maxHitPoints = this.hitPoints;
    }

    public int getHitPoints() {
        return this.hitPoints;
    }

    public void takeDamage(int damage) {

    }

    public int attack() {
        return -1;
    }

    private int setInitialHitPoints() {
        final int rolls = 6;
        int total = 0; // local variable
        for(int i = 0; i < rolls; ++i) {
            d10.roll();
            total += d10.getCurrentValue();
        }
        return total;
    }

    private int ai() {
        return -1;
    }
}
