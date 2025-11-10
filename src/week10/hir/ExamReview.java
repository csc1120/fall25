/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week10.hir;

import java.util.ArrayList;
import java.util.Scanner;

public class ExamReview {
    static int c = 6;

    public static void main(String[] args) {
        int a = 5; // initialization
        int b; // declaration
        b = 3; // assignment
        Scanner in = new Scanner(System.in);
        String s = "hello";
        String t = "hello";
        String u = "goodbye";
        System.out.println(s == t);
        String v = new String("hello");
        System.out.println(s == v);

        int[] nums = new int[3];
        nums[0] = 7;
        nums[2] = 2;
        for(int i : nums) {
            System.out.println(i);
        }

        ArrayList<String> words = new ArrayList<>();
        words.add("green");
        words.add("cheese");
        words.add("Liam");
        System.out.println(nums);
        System.out.println(words);
        System.out.println(in);
        System.out.println(nums[0]);
    }
}
