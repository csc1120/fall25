/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week6.hir.session2;

public class Person {
    // instance variable
    private String name;
    private int age;
    private double height;

    /*
     * 1. public
     * 2. Exact same name as the class and the source file
     * 3. NO return type
     */
    public Person(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    /*
     * 1. public
     * 2. return type matches the variable
     * 3. naming is get<variable name>
     * 4. no parameters
     * 5. Return the value
     */
    public String getName() {
        return this.name;
    }

    /*
     * 1. public
     * 2. return type is void
     * 3. naming set<variable name>
     * 4. parameter must be new value
     * 5. set the variable to new value
     */
    public void setName(String name) {
        this.name = name;
    }
}
