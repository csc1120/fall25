/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week4.hir;

public class HIRConditionals2 {
    public static void main(String[] args) {
//        System.out.println();
//        int i = 5;
//        String s = "54";
//        s.length();
//        int b = Integer.parseInt(s);
//        System.out.println(b);
//        Integer d = 6;
//        double e = 5;
//        int c = 'a';
//        // utility class
//        int pow = (int) Math.pow(8, 4);
//        System.out.println(pow);
        // you know how long
        boolean five = false;
        for (int j = 0; j < 10 && !five; j++) {
            if(j == 5) {
                five = true;
            }
            for(int k = 0; k < 10; ++k) {
                System.out.println(j + k);
            }
        }
//
//        // you don't know how many
//        while(i < 10) {
//
//        }
//
//        // you don't know how many but at least once
//        do {
//
//        } while (i < 10);
    }
}
