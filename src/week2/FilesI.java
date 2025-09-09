/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FilesI {
    public static void main(String[] args) {
        Path path = Paths.get("data", "text", "fred.txt");
        File data = new File("data");
        System.out.println(data.exists());
        File file = new File("data/fred.txt");
        System.out.println(file.exists());
        System.out.println(file.length());
        try(PrintWriter pw = new PrintWriter(path.toFile())) { // try-with-resources
            pw.println("This is a line of text");
            pw.println("So is this");
            pw.println("Me too");
            System.out.println(file.getAbsolutePath());
            // auto-close
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(file.exists());
        System.out.println(file.length());

        try(Scanner read = new Scanner(file)) {
//            while(read.hasNext()) {
//                System.out.println(read.next());
//            }
            System.out.println(read.nextLine());
            System.out.println(read.nextLine());
            System.out.println(read.nextLine());
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
