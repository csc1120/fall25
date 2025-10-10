/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week6.hir.session2;

public class PersonDriver {
    public static void main(String[] args) {
        Person p; // declare
        p = new Person("Kyle", 25,70);
        String s = "hello";
        System.out.println();
        System.out.println(p.getName());
        Person p2 = new Person("Joe", 22, 65);
        System.out.println(p2.getName());
        Person p3 = new Person("Kyle", 25, 70);
        System.out.println(p == p3);
        Person p4 = p3;
        System.out.println(p3 == p4);

        String t = "hello";
        System.out.println(s == t);

        String u = new String("hello");
        System.out.println(t == u);
    }
}
