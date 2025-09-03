/*
 * Course: CSC-1120
 * Java Review
 * MSOEStudent
 * Name: Sean Jones
 * Last Updated: 09-03-25
 */
package week1;

/**
 * A concrete {@link Student} implementation for the glorious institution that is
 * The Milwaukee School of Engineering (MSOE). This class includes the typical
 * cripplingDebt that all students will be saddled with as well as a secret
 * that each MSOEStudent has.
 */
public class MSOEStudent extends Student {
    private static int studentID = 0;
    private double cripplingDebt;
    private final String secret;

    /**
     * A constructor for an MSOEStudent that includes their secret, as well
     * as the name and major. Their cripplingDebt will default to $1,000,000
     * and their gpa will default to 0.0
     * @param name the name of the MSOEStudent
     * @param major the major of the MSOEStudent
     * @param secret the MSOEStudent's deepest, darkest secret
     */
    public MSOEStudent(String name, String major, String secret) {
        super(name, major);
        final int defaultDebt = 1_000_000;

        this.gpa = 0.0;
        this.cripplingDebt = defaultDebt;
        this.secret = secret;
        studentID++;
    }

    public static int getStudentID() {
        return studentID;
    }

    /**
     * Allows the student to attempt to pay off their cripplingDebt
     * @param payment the amount paid towards their debt
     */
    public void payDebt(double payment) {
        this.cripplingDebt -= payment;
    }

    @Override
    public String shareSecret() {
        return this.secret;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nStudent ID: " + studentID
                + "\nDebt: " + String.format("$%.2f", this.cripplingDebt);
    }
}
