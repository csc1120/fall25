/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week12;

public class ShellSort implements SortAlgorithm{
    @Override
    public <T extends Comparable<T>> void sort(T[] table) {
        final double shellConstant = 2.2;
        int gap = table.length / 2;
        while(gap > 0) {
            for(int nextPos = gap; nextPos < table.length; ++nextPos) {
                // starting index = gap
                int nextInsert = nextPos;
                T nextVal = table[nextInsert];
                while(nextInsert > gap - 1 && nextVal.compareTo(table[nextInsert - gap]) < 0) {
                    // swap
                    table[nextInsert] = table[nextInsert - gap];
                    nextInsert -= gap;
                }
                table[nextInsert] = nextVal;
            }
            // reduce gap
            if(gap == 2) {
                gap = 1;
            } else {
                gap = (int) (gap /shellConstant);
            }
        }


    }
}
