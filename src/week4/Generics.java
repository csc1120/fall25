/*
 * Course: CSC-1120
 * Generics
 * Generics
 * Name: Sean Jones
 * Last Updated: 09-26-25
 */
package week4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * A bunch of Generics examples
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Generics {
    public static void main(String[] args) {
        List<String> words;
        words = new ArrayList<>();
        words.add("Hello");
        words.add("Goodbye");

        GenericClass<String> stringClass = new GenericClass<>("string");
        GenericClass reallyGeneric = new GenericClass("iobject");

        System.out.println(stringClass.getMyGenericVariable());
        System.out.println(reallyGeneric.getMyGenericVariable());
        System.out.println(stringClass.getMyGenericVariable().length());
        System.out.println(((String)reallyGeneric.getMyGenericVariable()).length());
        MyFunction<String, Integer> funct1 = new MyFunction<>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }

            @Override
            public Integer apply(String s, String u) {
                return s.length() + u.length();
            }

            @Override
            public int size() {
                return 0;
            }
        };

        System.out.println(funct1.apply(words.get(0)));
        System.out.println(funct1.apply(words.get(0), words.get(1)));
        System.out.println(funct1.size());

        Function<String, Integer> f = s -> s.indexOf('g');
        System.out.println(f.apply(words.get(1).toLowerCase()));

        BinaryOperator<Integer> bo = (i, j) -> i + j;
        System.out.println(bo.apply(1, 2));
        System.out.println("Hello");
        // In the String pool
        String s = "Hello";
        String t = "Hello";
        System.out.println(s == t); // The same location (same instance)
        // On the heap
        String heap = new String("Hello");
        System.out.println(s == heap); // Not in the same location
    }
}
