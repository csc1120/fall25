/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week6;

public class ListDriver {
    public static void main(String[] args) {
//        List<Integer> nums = Arrays.asList(5, 6, 8, 99, 45, 2, 6, 7, 2, 4, 4);
//        for(int i = 0; i < nums.size(); ++i) {
//            System.out.println(nums.get(i));
//        }

        SJLinkedList<Integer> nums2 = new SJLinkedList<>();
        nums2.add(4);
        nums2.add(3);
        nums2.add(7);
        nums2.add(1);
        for(Integer i : nums2) {
            System.out.println(i);
        }
    }
}
