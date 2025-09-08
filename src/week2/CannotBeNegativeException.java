/*
 * Course: CSC-1120
 * Exceptions II
 * CannotBeNegativeException
 * Name: Sean Jones
 * Last Updated: 09-08-25
 */
package week2;

/**
 * Exception class thrown when a number should be positive, but is not
 */
public class CannotBeNegativeException extends RuntimeException {
    /**
     * No-param constructor
     */
    public CannotBeNegativeException() {
        super();
    }

    /**
     * Constructor that includes an exception message for the user
     * @param message the message to add to the exception
     */
    public CannotBeNegativeException(String message) {
        super(message);
    }
}
