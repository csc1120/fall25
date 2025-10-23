/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week8;

import java.util.Scanner;

public class MoreObjects {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Math.random();
        Cat c = new Cat("Orange Tabby");
        c.setNumPaws(8);
        Cat c2 = new Cat("Maine Coon");
        System.out.println(c2);
        System.out.println(c == c2);
        Cat c3 = new Cat("Maine Coon");
        System.out.println(c2 == c3);
        System.out.println(c2.equals(c3));
        Cat c4 = c2;
    }
}
