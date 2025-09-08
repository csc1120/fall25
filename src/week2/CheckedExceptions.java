/*
 * Course: CSC-1120
 * Exceptions II
 * CheckedExceptions
 * Name: Sean Jones
 * Last Updated: 09-08-25
 */
package week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Examples of exceptions required by the JVM to compile.
 */
public class CheckedExceptions {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Scanner attached to keyboard input
        try {
            String lines = getFile(in);
            System.out.println(lines);
            printPositiveInteger(1);
            printPositiveInteger(-1);
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            // e.printStackTrace(); - not recommended
            System.exit(1);
        } finally {
            in.close(); // always close your streams
        }
    }

    /**
     * description
     * @param in Scanner to get filename from
     * @return the String stored in the file
     * @throws FileNotFoundException could not find file
     */
    private static String getFile(Scanner in) throws FileNotFoundException {
        File file = new File(in.nextLine());
        StringBuilder result = new StringBuilder();
        try(Scanner read = new Scanner(file)) {
            while(read.hasNextLine()) {
                result.append(read.nextLine());
            }
        }
        return result.toString();
    }

    private static void printPositiveInteger(int number) throws CannotBeNegativeException {
        if(number < 0) {
            throw new CannotBeNegativeException("Number should be positive but was : " + number);
        }
        System.out.println(number);
    }
}
