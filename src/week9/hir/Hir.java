/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week9.hir;

import week6.hir.session1.Person;

public class Hir {
    public static void main(String[] args) {
        int[] nums = new int[5];
        nums[0] = 2;
        nums[3] = 1;
        System.out.println(nums);
        Person p = new Person("name", 5, 2);
        System.out.println(p);
        System.out.println(nums[1]);
        String[] words = new String[5];
        System.out.println(words[0]);

        Person[] people = new Person[5];
        people[0] = p;
        people[1] = p;
//        System.out.println(people[5]);
        printArray(nums);
        incrementArray(nums);
        printArray(nums);
        System.out.println(nums[1] == nums[2]);

        // 2D
        int[][] matrix = new int[2][2];

    }

    private static void printArray(int[] nums) {
        for(int i = 0; i < nums.length; ++i) {
            System.out.println(nums[i]);
        }
    }

    private static void incrementArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i]++;
        }
    }
}
