/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week6.hir;

public class Person {
    public static final int DEFAULT_AGE = 20;
    // instance variables declare
    private String name;
    private final double height;
    private final int age;

    // constructor
    // 1. must be public
    // 2. exact same name as the class
    // 3. No return value
    // 4. ALL instance variables are initialized here
    public Person(String name, double height, int age) {
        this.name = name;
        this.height = height;
        this.age = age;
    }

    // getter rules
    // 1. always public
    // 2. always return type of the variable
    // 3. no parameter
    // 4. returns the instance variable
    public String getName() {
        return name;
    }

    // setter rules
    // 1. public
    // 2. void return
    // 3. have to have a parameter
    public void setName(String name) {
        this.name = name;
    }

}
