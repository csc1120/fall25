/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week9.hir;

import week6.hir.session1.Person;

import java.util.ArrayList;

public class ArrayStuff {
    static double f = 5.4;

    public static void main(String[] args) {
        int[] nums = new int[5];
        nums[0] = 2;
        nums[2] = 1;
        System.out.println(nums);
        System.out.println(nums[0]);
        System.out.println(nums[1]);

        Person[] people = new Person[5];
        System.out.println(people[0]);

        int[] nums2 = {4, 3, 2, 7, 8, 1};

        String s = "hello";

        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(3);
        System.out.println(list.get(1));
        for (int i = 0; i < 100000; i++) {
            double d = 5.5;
            list.add(i);
        }

        System.out.println(arraySize(nums));

        String t = "hello";
        System.out.println(s == t);
        String u = new String("hello");
        System.out.println(t == u);
        System.out.println(s.equals(u));
    }

    private static int arraySize(int[] arr) {
        return arr.length;
    }
}
