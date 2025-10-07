/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week5.hir;

public class Tracing {
    public static void main(String[] args) {
        int max = 8;
        int total = 1;
        int testNum = 0;
        int totalSum = 0;

        for(int i = 1; i < max; i++) {
            if(testNum % 2 == 0 && testNum % 3 == 0) {
                total++;
                totalSum += testNum;
            }
            testNum++;
        }
    }
}
