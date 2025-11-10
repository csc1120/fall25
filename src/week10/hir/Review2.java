/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week10.hir;

import week6.hir.session1.Person;

import java.util.ArrayList;

public class Review2 {
    public static void main(String[] args) {
        int a = 3;
        int b = 9;

        if(a % b == 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

        // ternary operator is a question, true response, false response
        System.out.println(b % a == 0 ? "yes" : "no");

        Person p = new Person("joe", 0, 0);
        int[] nums = new int[3];
        nums[0] = 1;
        nums[1] = 5;
        nums[2] = 6;
        String s = "hello";
        s = s + "s";

        for(int i = 0; i < nums.length; ++i) {
            System.out.println(nums[i]);
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        Integer f = 6;

        int[] arr = {4, 7, 3, 1};
        int t = arr[4];


    }
}
