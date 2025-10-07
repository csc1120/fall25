/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week5.hir;

public class Casting {
    public static void main(String[] args) {
        char c = 'c';
        int i = c;
        System.out.println(c);
        System.out.println(i);
        double d = 4.2;
        int j = (int) d;
        double e = i;
        String s = Integer.toString(i);
        System.out.println(s);
        double f = Double.parseDouble(s);
        String str = "4.3";
        int a = Integer.parseInt(s);

        String num = "5243";
        int parsed = Integer.parseInt(num);

        System.out.println("Hello");
        String h = "hello";
        System.out.println(h.substring(2));
    }
}
