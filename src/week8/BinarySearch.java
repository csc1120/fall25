/*
 * Course: CSC-1120
 * Binary Search and Recursion
 * Binary Search
 * Name: Sean Jones
 * Last Updated: 10-20-25
 */
package week8;

/**
 * Binary Search implemented procedurally (with loops)
 * Plus a bonus factorial recursive implementation
 */
public class BinarySearch {
    public static void main(String[] args) {
        final int[] numbers = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24};
        System.out.println(factorial(5));
        System.out.println(binarySearch(numbers, 12));
        System.out.println(binarySearch(numbers, 17));
        System.out.println(binarySearch(numbers, 2));
    }

    private static boolean binarySearch(int[] data, int target) {
        int numberOfComparisons = 0;
        boolean found = false;
        int begin = 0; // beginning range is entire list, so start index is 0
        int end = data.length - 1; // end index is length - 1
        while(begin < end && !found) {
            ++numberOfComparisons;
            int middle = (begin + end) / 2; // calculate the middle of the range
            if(data[middle] == target) { // found it!
                found = true;
            } else if(data[middle] < target) { // target is larger
                begin = middle + 1;
            } else { // target is smaller
                end = middle;
            }
        }
        System.out.println(numberOfComparisons);
        return found;
    }

    private static int factorial(int n) {
        // base case
        if(n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
