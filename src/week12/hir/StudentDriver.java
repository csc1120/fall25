/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week12.hir;

public class StudentDriver {
    public static void main(String[] args) {
        Course c1 = new Course("Data Structures", "CSC-1120");
        Course c2 = new Course("Physics II", "PHY-1120");
        Course c3 = new Course("Microeconomics", "BUS-1111");

        Student s = new Student("Bob", "brown", 60);
        s.addCourse(c1);
        s.addCourse(c2);
        s.addCourse(c3);

        System.out.println(s);
        Person p = s;
        Object o = s;
        System.out.println(o);

        Student t = new Student("Alice", "blonde", 64);

        System.out.println(s.equals(t));
    }
}
