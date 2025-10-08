/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 * More File Examples
 */
public class FilesII {
    @SuppressWarnings("CheckStyle")
    public static void main(String[] args) {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("fred.bin"))) {
            dos.writeInt(7);
            dos.writeDouble(5.5);
            dos.writeDouble(4.3);
            dos.writeChars("body");
        } catch(IOException e) {
            System.out.println("IO Error");
        }
        try(DataInputStream dis = new DataInputStream(new FileInputStream("fred.bin"))) {
            System.out.println(dis.readInt());
            System.out.println(dis.readDouble());
            System.out.println(dis.readDouble());
            System.out.println(dis.readUTF());
//            System.out.println(dis.readChar());
//            System.out.println(dis.readChar());
//            System.out.println(dis.readChar());
//            System.out.println(dis.readChar());
        } catch(IOException e) {
            System.out.println("IO Error");
        }

        MyObject myObject = new MyObject(7, 5.5, 4.3, "body");

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.bin"))) {
            oos.writeObject(myObject);
        } catch(IOException e) {
            System.out.println("IO Error");
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.bin"))) {
            MyObject obj2 = (MyObject) ois.readObject();
            System.out.println(obj2);
        } catch(IOException e) {
            System.out.println("IO Error");
        } catch(ClassNotFoundException e) {
            System.out.println("Class not defined");
        }

        try(PrintWriter pw = new PrintWriter(new FileOutputStream("fred2.txt", true))) {
            pw.println("Line 1");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
