/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week14;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ExceptionsAndFileIO {
    public static void main(String[] args) {
//        Scanner in = new Scanner("hello");
//        System.out.println(in.next());
        Path p = Paths.get("data", "test", "test.txt");
        try (Scanner in = new Scanner(p);
             PrintWriter pw = new PrintWriter("output.txt")){
            System.out.println(in.nextLine());
            pw.println("This is a line.");
        } catch(IOException e) {
            System.err.println("Could not find path");
        }
    }
}
