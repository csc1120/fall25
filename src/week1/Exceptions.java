/*
 * Course: CSC-1120
 * Exceptions Examples
 * Exceptions
 * Name: Sean Jones
 * Last Updated: 09-05-25
 */
package week1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Dumb program for dummy users. With Exceptions.
 */
public class Exceptions {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = -1;
        int b = -1;
        do {
            try {
                System.out.print("Enter two positive integers: ");
                a = in.nextInt(); // dangerous code
                b = in.nextInt(); // dangerous code
                if(a < 0 || b < 0) {
                    throw new InputMismatchException("I said positive. Dummy.");
                }
                System.out.println("The quotient is " + a / b); // dangerous code
            } catch (InputMismatchException e) {
                in.nextLine();
                if(e.getMessage() != null){
                    System.out.println(e.getMessage());
                }
                System.out.println("Enter an integer, dummy");
            } catch(ArithmeticException e){
                System.out.println("Dividing by 0 is bad");
            } finally {
                System.out.println("always happens");
            }
        } while(a < 0 || b < 0);

    }
}