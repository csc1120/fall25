/*
 * Course: CSC-1120
 * Java Review
 * Student
 * Name: Sean Jones
 * Last Updated: 09-03-25
 */
package week1;

/**
 * A generic Student that implements the {@link Person} interface.
 * The only abstract method in this class is the {@link Person#shareSecret()}
 * method.
 */
public abstract class Student implements Person {
    protected double gpa;
    private final String name;
    private String major;

    /**
     * Constructor for a Student that sets the Student's name and major.
     * The gpa defaults to 0.0
     * @param name the name of the Student
     * @param major the major of the Student
     */
    public Student(String name, String major) {
        this.name = name;
        this.major = major;
        this.gpa = 0.0;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\nMajor: " + this.major;
    }
}
