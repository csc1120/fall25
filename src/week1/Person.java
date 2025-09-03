/*
 * Course: CSC-1120
 * Java Review
 * Person
 * Name: Sean Jones
 * Last Updated: 09-03-25
 */
package week1;

/**
 * A simple Person interface. All people have secrets...
 */
public interface Person {
    /**
     * Allows a Person to share their deepest, darkest secret
     * @return a secret, so it is not so secret anymore
     */
    String shareSecret();
}
