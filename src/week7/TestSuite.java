/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSuite {
    private SJDoubleLinkedList<String> list;

    @BeforeEach
    void setup() {
        list = new SJDoubleLinkedList<>();
    }

    @Test
    void testAdd() {
        // make sure adding actually adds

        // if list has three elements, does it add?

        // if list is empty
        Assertions.assertTrue(list.add("a"));
        Assertions.assertEquals("a", list.get(0));
        Assertions.assertEquals(1, list.size());
        Assertions.assertTrue(list.add("b"));
        Assertions.assertTrue(list.add("c"));
        Assertions.assertTrue(list.add("d"));
        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals("d", list.get(3));
    }
}
