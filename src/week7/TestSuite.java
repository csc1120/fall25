/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week7;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import week6.SJLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Basic Test Suite for the simplified SJLinkedList implementation of
 * a Single Linked List
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// CHECKSTYLE:OFF MagicNumber
@SuppressWarnings({"ConstantConditions", "MismatchedQueryAndUpdateOfCollection",
        "Suspicious collections method calls"})
public class TestSuite {
    // Helper Methods

    @SafeVarargs
    private static <T> SJLinkedList<T> listOf(T... items) {
        SJLinkedList<T> list = new SJLinkedList<>();
        Collections.addAll(list, items);
        return list;
    }

    private static SJLinkedList<Integer> seq(int n) {
        SJLinkedList<Integer> list = new SJLinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return list;
    }

    // ---------- Tests ----------

    @Nested
    @DisplayName("01 Construction & basic properties")
    @Order(1)
    class Basics {
        @Test
        @Order(1)
        @DisplayName("New list is empty with size == 0")
        void newListEmpty() {
            SJLinkedList<String> list = new SJLinkedList<>();
            Assertions.assertTrue(list.isEmpty());
            Assertions.assertEquals(0, list.size());
        }

        @Test
        @Order(2)
        @DisplayName("size() grows with add() and isEmpty() reflects content")
        void sizeAndIsEmpty() {
            SJLinkedList<Integer> list = new SJLinkedList<>();
            Assertions.assertTrue(list.isEmpty());
            list.add(1);
            list.add(2);
            Assertions.assertEquals(2, list.size());
            Assertions.assertFalse(list.isEmpty());
        }
    }

    @Nested
    @DisplayName("02 add / get / set / index validation")
    @Order(2)
    class AddGetSet {
        @Test
        @Order(1)
        @DisplayName("add(e) appends; get(i) returns correct elements")
        void addAppendsAndGetWorks() {
            SJLinkedList<Integer> list = new SJLinkedList<>();
            list.add(10);
            list.add(20);
            list.add(30);
            Assertions.assertEquals(3, list.size());
            Assertions.assertEquals(10, list.get(0));
            Assertions.assertEquals(20, list.get(1));
            Assertions.assertEquals(30, list.get(2));
        }

        @Test
        @Order(2)
        @DisplayName("add(index,e) inserts at head, middle, and tail")
        void addAtIndex() {
            SJLinkedList<Integer> list = listOf(1, 3, 4);
            list.add(0, 0);       // head
            list.add(2, 2);       // middle
            list.add(list.size(), 5); // tail/append
            Assertions.assertIterableEquals(Arrays.asList(0, 1, 2, 3, 4, 5),
                    Arrays.asList(list.toArray(new Integer[0])));
        }

        @Test
        @Order(3)
        @DisplayName("set(index,e) replaces and returns old value")
        void setReplaces() {
            SJLinkedList<String> list = listOf("a", "b", "c");
            String old = list.set(1, "B");
            Assertions.assertEquals("b", old);
            Assertions.assertIterableEquals(Arrays.asList("a", "B", "c"),
                    Arrays.asList(list.toArray(new String[0])));
        }

        @Test
        @Order(4)
        @DisplayName("get/set/add(index) validate bounds")
        void indexValidation() {
            SJLinkedList<Integer> list = listOf(1, 2, 3);
            Assertions.assertThrows(IndexOutOfBoundsException.class,
                    () -> list.get(-1));
            Assertions.assertThrows(IndexOutOfBoundsException.class,
                    () -> list.get(3));
            Assertions.assertThrows(IndexOutOfBoundsException.class,
                    () -> list.set(3, 99));
            Assertions.assertThrows(IndexOutOfBoundsException.class,
                    () -> list.add(5, 7));

            // add at size is allowed (append)
            Assertions.assertDoesNotThrow(() -> list.add(list.size(), 4));
            Assertions.assertEquals(4, list.size());
        }
    }

    @Nested
    @DisplayName("03 remove by index / remove by object")
    @Order(3)
    class RemoveOps {
        @Test
        @Order(1)
        @DisplayName("remove(index) removes head, middle, and tail")
        void removeByIndex() {
            SJLinkedList<Integer> list = listOf(10, 20, 30, 40);
            Assertions.assertEquals(10, list.remove(0)); // head
            Assertions.assertEquals(30, list.remove(1)); // middle (after removal, list: 20,30,40)
            Assertions.assertEquals(40, list.removeLast()); // tail
            Assertions.assertIterableEquals(Collections.singletonList(20),
                    Arrays.asList(list.toArray(new Integer[0])));
            Assertions.assertEquals(1, list.size());
        }

        @Test
        @Order(2)
        @DisplayName("remove(Object) removes first match and returns true; false if absent")
        void removeByObject() {
            SJLinkedList<String> list = listOf("a", "b", "c", "b");
            Assertions.assertTrue(list.remove("b"));
            Assertions.assertIterableEquals(Arrays.asList("a", "c", "b"),
                    Arrays.asList(list.toArray(new String[0])));
            Assertions.assertTrue(list.remove("b"));
            Assertions.assertIterableEquals(Arrays.asList("a", "c"),
                    Arrays.asList(list.toArray(new String[0])));
            Assertions.assertFalse(list.remove("z"));
        }

        @Test
        @Order(3)
        @DisplayName("remove(Object) handles head removal and singleton list")
        void removeHeadAndSingleton() {
            SJLinkedList<Integer> singleton = listOf(42);
            Assertions.assertTrue(singleton.remove(Integer.valueOf(42)));
            Assertions.assertTrue(singleton.isEmpty());

            SJLinkedList<Integer> list = listOf(1, 2, 3);
            Assertions.assertTrue(list.remove(Integer.valueOf(1)));
            Assertions.assertIterableEquals(Arrays.asList(2, 3),
                    Arrays.asList(list.toArray(new Integer[0])));
        }
    }

    @Nested
    @DisplayName("04 bulk ops: addAll(c) / addAll(index,c) / containsAll(c)")
    @Order(4)
    class BulkOps {
        @Test
        @Order(1)
        @DisplayName("addAll(c) on empty and non-empty lists preserves order")
        void addAllAppends() {
            SJLinkedList<Integer> a = new SJLinkedList<>();
            Assertions.assertTrue(a.addAll(List.of(1, 2, 3)));
            Assertions.assertIterableEquals(Arrays.asList(1, 2, 3),
                    Arrays.asList(a.toArray(new Integer[0])));

            Assertions.assertTrue(a.addAll(List.of(4, 5)));
            Assertions.assertIterableEquals(Arrays.asList(1, 2, 3, 4, 5),
                    Arrays.asList(a.toArray(new Integer[0])));
            Assertions.assertEquals(5, a.size());
        }

        @Test
        @Order(2)
        @DisplayName("addAll(index,c) splices chain at head, middle, and tail")
        void addAllAtIndex() {
            SJLinkedList<Integer> list = listOf(1, 4);
            Assertions.assertTrue(list.addAll(0, List.of(0)));          // head
            Assertions.assertTrue(list.addAll(2, List.of(2, 3)));        // middle
            Assertions.assertTrue(list.addAll(list.size(), List.of(5))); // tail
            Assertions.assertIterableEquals(Arrays.asList(0, 1, 2, 3, 4, 5),
                    Arrays.asList(list.toArray(new Integer[0])));
        }

        @Test
        @Order(3)
        @DisplayName("containsAll(c) true only when every element is present")
        void containsAllWorks() {
            SJLinkedList<String> list = listOf("a", "b", "c");
            Assertions.assertTrue(list.containsAll(List.of("a", "c")));
            Assertions.assertFalse(list.containsAll(List.of("a", "d")));
            Assertions.assertTrue(list.containsAll(List.of()));
        }

        @Test
        @Order(4)
        @DisplayName("addAll(c) with self as argument duplicates sequence (self-add)")
        void addAllSelf() {
            SJLinkedList<Integer> list = listOf(1, 2, 3);
            Assertions.assertTrue(list.addAll(list)); // should end up 1,2,3,1,2,3
            Assertions.assertIterableEquals(Arrays.asList(1, 2, 3, 1, 2, 3),
                    Arrays.asList(list.toArray(new Integer[0])));
            Assertions.assertEquals(6, list.size());
        }
    }

    @Nested
    @DisplayName("05 toArray() / toArray(T[])")
    @Order(5)
    class ToArrayOps {
        @Test
        @Order(1)
        @DisplayName("toArray() returns Object[] with correct order")
        void toArrayObject() {
            SJLinkedList<Integer> list = listOf(7, 8, 9);
            Object[] arr = list.toArray();
            Assertions.assertArrayEquals(new Object[] {7, 8, 9}, arr);
        }

        @Test
        @Order(2)
        @DisplayName("toArray(T[]) sized 0 allocates correct type and copies elements")
        void toArrayGenericAllocates() {
            SJLinkedList<String> list = listOf("x", "y");
            String[] out = list.toArray(new String[0]);
            Assertions.assertArrayEquals(new String[] {"x", "y"}, out);
        }

        @Test
        @Order(3)
        @DisplayName("toArray(T[]) larger array gets null terminator")
        void toArrayGenericLarger() {
            SJLinkedList<String> list = listOf("a", "b", "c");
            String[] out = new String[5];
            Arrays.fill(out, "z");
            out = list.toArray(out);
            Assertions.assertArrayEquals(new String[] {"a", "b", "c", null, "z"}, out);
        }
    }

    @Nested
    @DisplayName("06 contains / indexOf / lastIndexOf")
    @Order(6)
    class SearchOps {
        @Test
        @Order(1)
        @DisplayName("contains(o) uses Objects.equals semantics (supports null)")
        void containsSupportsNull() {
            SJLinkedList<String> list = listOf("a", null, "b");
            Assertions.assertTrue(list.contains("a"));
            Assertions.assertTrue(list.contains(null));
            Assertions.assertFalse(list.contains("z"));
        }

        @Test
        @Order(2)
        @DisplayName("indexOf and lastIndexOf report first/last positions")
        void indexPositions() {
            SJLinkedList<Integer> list = listOf(1, 2, 3, 2, 1);
            Assertions.assertEquals(0, list.indexOf(1));
            Assertions.assertEquals(4, list.lastIndexOf(1));
            Assertions.assertEquals(1, list.indexOf(2));
            Assertions.assertEquals(3, list.lastIndexOf(2));
            Assertions.assertEquals(2, list.indexOf(3));
            Assertions.assertEquals(2, list.lastIndexOf(3));
            Assertions.assertEquals(-1, list.indexOf(99));
        }

        @Test
        @Order(3)
        @DisplayName("indexOf(null) on a list containing null currently throws NPE")
        void indexOfNullBehavior() {
            // Documenting current implementation behavior:
            // indexOf uses current.data.equals(o), which NPEs if data is null.
            SJLinkedList<String> list = listOf("a", null, "b");
            Assertions.assertThrows(NullPointerException.class, () -> list.indexOf(null));
            Assertions.assertThrows(NullPointerException.class, () -> list.lastIndexOf(null));
        }
    }

    @Nested
    @DisplayName("07 iterator contract via public iterator()")
    @Order(7)
    class IteratorContract {
        @Test
        @Order(1)
        @DisplayName("Iterator traverses all elements in order")
        void iterateAll() {
            SJLinkedList<Integer> list = seq(5); // 0..4
            Iterator<Integer> it = list.iterator();
            List<Integer> got = new ArrayList<>();
            while (it.hasNext()) {
                got.add(it.next());
            }
            Assertions.assertEquals(Arrays.asList(0, 1, 2, 3, 4), got);
        }

        @Test
        @Order(2)
        @DisplayName("Iterator.remove() removes last returned element; alternating removals")
        void iteratorRemoveAlternating() {
            SJLinkedList<Integer> list = seq(6); // 0..5
            Iterator<Integer> it = list.iterator();
            int idx = 0;
            while (it.hasNext()) {
                it.next();
                if (idx % 2 == 0) {
                    it.remove(); // remove even indices
                }
                idx++;
            }
            // Expect only original odd indices remain: 1,3,5
            Assertions.assertIterableEquals(Arrays.asList(1, 3, 5),
                    Arrays.asList(list.toArray(new Integer[0])));
            Assertions.assertEquals(3, list.size());
        }

        @Test
        @Order(3)
        @DisplayName("Iterator.remove() before next() throws IllegalStateException")
        void removeBeforeNextIllegal() {
            SJLinkedList<Integer> list = seq(3);
            Iterator<Integer> it = list.iterator();
            Assertions.assertThrows(IllegalStateException.class, it::remove);
        }

        @Test
        @Order(4)
        @DisplayName("Consecutive remove() without next() throws IllegalStateException")
        void consecutiveRemoveIllegal() {
            SJLinkedList<Integer> list = listOf(10, 20);
            Iterator<Integer> it = list.iterator();
            it.next();
            it.remove();
            Assertions.assertThrows(IllegalStateException.class, it::remove);
        }

        @Test
        @Order(5)
        @DisplayName("next() on exhausted iterator throws NoSuchElementException")
        void nextOnExhaustedThrows() {
            SJLinkedList<Integer> list = listOf(1);
            Iterator<Integer> it = list.iterator();
            Assertions.assertEquals(1, it.next());
            Assertions.assertThrows(NoSuchElementException.class, it::next);
        }
    }

    @Nested
    @DisplayName("08 clear() semantics")
    @Order(8)
    class ClearOps {
        @Test
        @Order(1)
        @DisplayName("clear() empties list and resets size")
        void clearEmpties() {
            SJLinkedList<Integer> list = seq(4);
            list.clear();
            Assertions.assertTrue(list.isEmpty());
            Assertions.assertEquals(0, list.size());
            Assertions.assertArrayEquals(new Object[] {}, list.toArray());
        }
    }
}