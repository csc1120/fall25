/*
 * Course: CSC-1120
 * Java Review
 * JavaReview
 * Name: Sean Jones
 * Last Updated: 09-03-25
 */
package week1;

import java.util.Scanner;

/**
 * A simple driver class that demonstrates the Java Review
 */
public class JavaReview {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Person[] people = new Person[3];

        for(int i = 0; i < people.length; ++i) {
            people[i] = makeMSOEStudent(in);
        }

        for(Person p : people) {
            System.out.println(p);
        }
    }

    private static Person makeMSOEStudent(Scanner in) {
        System.out.print("Enter student's name: ");
        String name = in.nextLine();
        System.out.print("Enter student's major: ");
        String major = in.nextLine();
        System.out.println("Enter student's secret: ");
        String secret = in.nextLine();
        return new MSOEStudent(name, major, secret);
    }
}
