/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Streams examples
 */
public class Streams {
    public static void main(String[] args) {
        Predicate<String> beginsWithS = s -> s.startsWith("s");
        System.out.println(beginsWithS.test("sandwich"));
        System.out.println(beginsWithS.test("potato chips"));
        Function<String, Integer> f = s -> s.length();
        System.out.println(f.apply("hello"));

        List<String> words = new ArrayList<>();
        words.add("She");
        words.add("sells");
        words.add("seashells");
        words.add("by");
        words.add("the");
        words.add("Seashore");
        Consumer<String> shout = s -> System.out.println(s.toUpperCase());
        words.stream().forEach(shout);

        // foreach - do the same thing to each piece of data
        // map - apply some function to each piece of data
        // filter - filters out some data - only true is allowed in the stream
        // reduce - reduce to a single value
        // collect - collects the remaining values into some collection

        List<String> result = words.stream().toList();
        System.out.println(result);
        int count = (int) words.stream()
                .filter(s -> s.startsWith("s"))
                .count();
        System.out.println(count);
        count = (int) words.stream()
                .map(s -> s.toLowerCase())
                .filter(s -> s.startsWith("s"))
                .count();
        System.out.println(count);

        words.parallelStream()
                .map(s -> s.length())
                .forEach(i -> System.out.println(i));
    }
}
