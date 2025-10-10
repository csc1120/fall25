/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * Examples of JUnit tests using the SJLinkedList class
 */
@SuppressWarnings("CheckStyle")
public class TestSuite {
    private SJLinkedList<String> list;

    @BeforeEach
    void setup() {
        list = new SJLinkedList<>();
    }

    @Test
    @DisplayName("Verify size changes with adding and removing")
    void testSize() {
        Assertions.assertEquals(0, list.size());
        list.add("hello");
        Assertions.assertEquals(1, list.size());
        list.add("hi");
        Assertions.assertEquals(2, list.size());
        list.remove("hello");
        Assertions.assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Test adding at an index")
    void addAtIndex() {
        fillList();
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.add(-1, "aloha"));
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> list.add(10, "aloha"));
        list.add(9, "aloha");
        Assertions.assertEquals(10, list.size());
        Assertions.assertEquals("aloha", list.get(9));
    }

    @Test
    @DisplayName("Testing contains")
    void testContains() {
        fillList();
        Assertions.assertTrue(list.contains("hello"));
        Assertions.assertFalse(list.contains("aloha"));
    }

    private void fillList() {
        String[] words = {"hello", "hi", "yo", "ahoy", "sup",
                "howdy", "salutations", "G'day", "Top o' the mornin'"};
        Collections.addAll(list, words);
    }
}
