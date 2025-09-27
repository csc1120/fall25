/*
 * Course: CSC-1120
 * Generics
 * MyFunction
 * Name: Sean Jones
 * Last Updated: 09-26-25
 */
package week4;

/**
 * An interface that uses generics, but NOT a Functional Interface
 * @param <T> the type of paramter allowed
 * @param <R> the return type of the interface
 */
public interface MyFunction<T, R> {
    /**
     * Applies a function to an element
     * @param t the element type used in the function
     * @return the result of the function possibly a different type than t
     */
    R apply(T t);
    /**
     * Applies a function to two elements of the same type
     * @param t the element type used in the function
     * @param u another instance of the same type of element as t
     * @return the result of the function possibly a different type than t
     */
    R apply(T t, T u);

    /**
     * Returns the size of....something
     * @return the size of....something
     */
    int size();
}
