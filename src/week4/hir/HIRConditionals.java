/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week4.hir;

import java.util.Scanner;

/**
 * Conditionals, Boolean Logic
 */
public class HIRConditionals {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        System.out.print("How much did you make last year? ");
//        int input = in.nextInt();
//        if(input < 10_000 && input > 5_000) {
//            System.out.println("You're poor.");
//        } else if(input <= 5_000) {
//            System.out.println("You're really poor.");
//        } else {
//            System.out.println("Did you even work?");
//        }
        System.out.print("Enter a word: ");
        String word = in.next();
        System.out.print("Enter another word: ");
        String word2 = in.next();
        // charAt, substring, length
        System.out.println("The length of word is: " + word.length());
        System.out.println("The length of word2 is: " + word2.length());
        System.out.println("The second letter of word is: " + word.charAt(1));
        System.out.println("The second letter of word2 is: " + word2.charAt(1));
        System.out.println("The characters 2-4 of word are: " + word.substring(1, 4));
        System.out.println("The characters 3-end of the word2 are: "+ word2.substring(2));

    }
}
