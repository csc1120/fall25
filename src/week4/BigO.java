/*
 * Course: CSC-1020
 * Big-O Examples
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week4;

import java.util.Arrays;

/**
 * Examples of calculating Big-O
 */
public class BigO {
    public static void main(String[] args) {
        final Integer[] nums = new Integer[] {4, 7, 1, 3, 9, 0, 8, 5, 2, 6};
        for (int i = 0; i < nums.length; i++) { // outer loop occurs n times
            for(int j = 0; j < nums.length; ++j) {
                System.out.println(nums[j]); // inner loop occurs n times
            }
        } // n x n = O(n^2)

        int k = 3;
        for(int i = 0; i < k; ++i) {
            System.out.println(Arrays.toString(nums)); // k is not related to n at all - O(m)
        }

        System.out.println(Arrays.toString(nums)); // No relation to any variable
        System.out.println(nums.length); // - O(1) constant time

        for(int i = 0; i < nums.length; ++i) { // outer loop occurs n times
            for(int j = i; j < nums.length; ++j) { // inner loop occurs n/2 times
                System.out.println(nums[j]); // 1/2n^2 = O(n^2) no coefficients in Big-O
            }
        }
    }
}
