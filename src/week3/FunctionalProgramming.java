/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week3;

public class FunctionalProgramming {
    public static void main(String[] args) {
        int x = 7;
        int y = 6;

        IntegerMathStuff add = (a, b) -> a + b;
        System.out.println(add.doMath(x, y));

        System.out.println(allTheMath(4, 2, (a,b ) -> a * b));
        System.out.println(allTheMath(4, 2, (a,b ) -> {
            int c = a + b;
            return "This is a String".length();
        }));
        System.out.println(allTheMath(9, 37, add));


        String line = "In the course of human events, we made pizza. And it was good. Very good. Like, really, really good. I mean, it was pizza.";
        String upper = makeItShout(line, s -> s.toUpperCase());
        String shouting = makeItShout(upper, s -> s.replace(".", "!"));
        shouting = makeItShout(shouting, s -> s.replace(" ", "  "));
        shouting = makeItShout(shouting, s -> s + "!!!!!!!!!!!!!!");
        System.out.println(shouting);
    }

    private static int allTheMath(int a, int b, IntegerMathStuff ims) {
        return ims.doMath(a, b);
    }

    private static String makeItShout(String s, StringStuff ss) {
        return ss.apply(s);
    }
}
