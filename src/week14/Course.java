/*
 * Course: CSC-1120
 * ASSIGNMENT
 * CLASS
 * Name: Sean Jones
 * Last Updated:
 */
package week14;

import java.util.ArrayList;

public class Course {
    private ArrayList<Student> students;
    private String code;

    public Course(String code) {
        this.code = code;
        students = new ArrayList<>();
    }

    public void addStudent(Student s) {
        students.add(s);
    }
}
