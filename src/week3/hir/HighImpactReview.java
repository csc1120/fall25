/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week3.hir;

/**
 * Javadoc comment
 */
public class HighImpactReview {
    public static void main(String[] args) {
        int i = 0; // count variable. Must be before the loop.
        // while loop - when you do not know how many times you will loop
        while (i != 10) {
            System.out.println(i);
            i = i + 1;
            // i++; many ways to increment a value
            // ++i;
            // i += 1;
        }

        i = 0; // reset i to zero for the next loop

        // do-while loop, when you must do the loop at least one time
        do {
            System.out.println(i);
            i = i + 1;
        } while (i != 10);

        /*
         * This is a block or multiline comment
         */
        String s = "hello"; // initialization
        int a; // declaration 32 bits
        a = 5; // assignment
        long l = 1000; // auto-promoting 64-bits
        long l2 = 1_000_000_000_000L; // long literal adds "L" at the end
        double d = 3.14; // 64-bits
        float f = 3.14F; // float literal adds "F" at the end
        char c = 'h'; // literal value 'c'
        boolean b = true; // only true or false

        // String is a "reference variable" and has methods
        // Call the methods using the "dot" operator -> s.<method>
        System.out.println(s.length()); // returns the length of the String as an int
        // Computer Science (almost) always starts counting at 0
        // charAt returns the character at the given index, starting at index 0
        System.out.println(s.charAt(2));
        String longString = "This is an entire sentence full of many words and characters";
        // contains checks if the given String is contained in another String
        System.out.println(longString.contains("full"));
    }
}
