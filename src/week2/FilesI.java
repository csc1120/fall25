/*
 * Course: CSC-1120
 * Files I
 * FilesI
 * Name: Sean Jones
 * Last Updated: 09-09-25
 */
package week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Introduction to Text Files and File and Path objects
 */
public class FilesI {
    public static void main(String[] args) {
        // Creating file
        File file = new File("fred.txt");
        // Creating directory
        Path dir = Paths.get("data");
        if(dir.toFile().mkdir()) {
            System.out.println("Create directory " + dir);
        }
        // Create nested directory
        Path dirs = Paths.get("data", "text");
        if(dirs.toFile().mkdirs()) {
            System.out.println("Created directory " + dirs);
        }

        try(PrintWriter pw = new PrintWriter(file)) {
            if(file.createNewFile()) {
                System.out.println("Created file: " + file);
            }
            pw.println("This is a text file");
            pw.println("It really is");
            pw.println("I mean it");
        } catch(FileNotFoundException e) {
            System.out.println("Cannot open file");
        } catch (IOException e) {
            System.out.println("Cannot create file");
        }
        System.out.println(file.length());

        try(Scanner read = new Scanner(file)) {
            while(read.hasNextLine()) {
                System.out.println(read.nextLine());
            }
        } catch(FileNotFoundException e) {
            System.out.println("Cannot read file");
        }
    }
}
