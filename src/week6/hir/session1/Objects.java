/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week6.hir.session1;

public class Objects {
    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i);
//        }

        Person p;
        p = new Person("Joe", 170, 40);
        System.out.println(p.getName());
        p.setName("Jack");
        System.out.println(p.getName());

        Person p1 = new Person("Nancy", 160, 50);
        System.out.println(p1.getName());
        System.out.println(p);

        String s = "hello";
        String t = "hello";
        System.out.println(s == t);
        int i = 5;

        Person p3 = new Person("Jack", 170, 40);
        System.out.println(p == p3);

        String u = new String("hello");
        System.out.println(s == u);
        System.out.println(s.equals(u));
    }
}
