/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week12.hir;

import java.util.ArrayList;

public class Student extends Person {
    private ArrayList<Course> courses;

    public Student(String name, String hair, int height) {
        super(name, hair, height);
        courses = new ArrayList<>();
    }

    public void addCourse(Course c) {
        courses.add(c);
    }

    @Override
    public String talk() {
        return "";
    }

    @Override
    public String toString() {
        return courses.toString();
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    // overloading
    public boolean equals(Student s) {
        return true;
    }
}
