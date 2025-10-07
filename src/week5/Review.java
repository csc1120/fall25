/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week5;

public class Review {
    public static void main(String[] args) {
        MyFunction<String, String, Integer> f =
                (s, t) -> s.length() + t.length();
    }

    @FunctionalInterface
    private interface MyFunction<T, U, R> {
        R apply(T t, U u);
    }
}
