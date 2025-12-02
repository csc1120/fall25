/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week12.hir;

public abstract class Person {
    protected String name;
    protected String hair;
    protected int height;

    public Person(String name, String hair, int height) {
        this.name = name;
        this.hair = hair;
        this.height = height;
    }

    public int walk() {
        return 0;
    }

    public int sleep() {
        return 0;
    }

    public abstract String talk();

    @Override
    public String toString() {
        return name;
    }
}
