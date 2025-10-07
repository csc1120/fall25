/*
 * Course: CSC-1020
 * ArrayList Tests
 * Name: Sean Jones
 * L:ast Updated: 10-07-25
 */
package week5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Test suite for week5.SJArrayList using nested test classes and ordered tests.
 */
@TestMethodOrder(OrderAnnotation.class)
@SuppressWarnings({"unchecked", "rawtypes", "ConstantConditions",
        "MismatchedQueryAndUpdateOfCollection", "EqualsWithItself"})
public class TestSuite {
    private final Class<SJArrayList> sjArrayListRaw = SJArrayList.class;

    @Nested
    @Order(1)
    @DisplayName("SJArrayList Structural tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class SJArrayListStructuralTests {
        @Nested
        @Order(1)
        @DisplayName("Class Structure")
        class ClassStructure {
            @Test
            @Order(1)
            @DisplayName("SJArrayList is public and implements List")
            void testClassModifiersAndInterfaces() {
                int modifiers = sjArrayListRaw.getModifiers();
                Assertions.assertTrue(Modifier.isPublic(modifiers), "SJArrayList must be public");
                Assertions.assertTrue(List.class.isAssignableFrom(sjArrayListRaw),
                        "SJArrayList should implement java.util.List");
            }

            @Test
            @Order(2)
            @DisplayName("SJArrayList declares one generic type parameter")
            void testTypeParameters() {
                Assertions.assertEquals(1, sjArrayListRaw.getTypeParameters().length,
                        "SJArrayList should declare exactly one generic type parameter");
            }
        }

        @Nested
        @Order(2)
        @DisplayName("Constructors")
        class Constructors {
            @Test
            @Order(1)
            @DisplayName("Public no-argument constructor exists and creates an empty list")
            void defaultConstructor() {
                SJArrayList<String> list = new SJArrayList<>();
                Assertions.assertEquals(0, list.size(), "New list should have size 0");
                Assertions.assertTrue(list.isEmpty(), "New list should be empty");
            }

            @Test
            @Order(2)
            @DisplayName("Capacity constructor accepts starting capacity (behavioral check)")
            void capacityConstructor() {
                final int[] values = {42, 13};
                SJArrayList<Integer> list = new SJArrayList<>(1);
                list.add(values[0]);
                list.add(values[1]);
                Assertions.assertEquals(List.of(values[0], values[1]), new ArrayList<>(list),
                        "Elements should be preserved");
            }

            @Test
            @Order(3)
            @DisplayName("Collection constructor copies all elements in order")
            void collectionConstructor() {
                List<String> src = List.of("a", "b", "c");
                SJArrayList<String> list = new SJArrayList<>(src);
                Assertions.assertEquals(src, new ArrayList<>(list),
                        "List contents should match source");
            }
        }
    }

    @Nested
    @Order(2)
    @DisplayName("Basic List Operations")
    @TestMethodOrder(OrderAnnotation.class)
    class BasicOperations {
        @Test
        @Order(1)
        @DisplayName("add(E) appends and size() reflects changes")
        void addAppends() {
            SJArrayList<String> list = new SJArrayList<>();
            Assertions.assertTrue(list.add("A"));
            Assertions.assertTrue(list.add("B"));
            Assertions.assertEquals(2, list.size());
            Assertions.assertIterableEquals(List.of("A", "B"), list);
        }

        @Test
        @Order(2)
        @DisplayName("add(index, E) inserts at index and shifts subsequent elements")
        void addAtIndex() {
            SJArrayList<Integer> list = new SJArrayList<>();
            list.add(1);
            list.add(3);
            list.add(1, 2);
            Assertions.assertIterableEquals(List.of(1, 2, 3), list);
        }

        @Test
        @Order(3)
        @DisplayName("get(index) retrieves element; set(index, E) replaces and returns old value")
        void getSetByIndex() {
            SJArrayList<String> list = new SJArrayList<>(List.of("x", "y", "z"));
            Assertions.assertEquals("y", list.get(1));
            String old = list.set(1, "Y");
            Assertions.assertEquals("y", old);
            Assertions.assertIterableEquals(List.of("x", "Y", "z"), list);
        }

        @Test
        @Order(4)
        @DisplayName("contains, indexOf, lastIndexOf work as expected")
        void containsAndIndexing() {
            final Integer[] values = {7, 1, 7, 2};
            final int badValue = 99;
            SJArrayList<Integer> list = new SJArrayList<>(List.of(values));
            Assertions.assertTrue(list.contains(values[0]));
            Assertions.assertEquals(0, list.indexOf(values[0]));
            Assertions.assertEquals(2, list.lastIndexOf(values[2]));
            Assertions.assertEquals(-1, list.indexOf(badValue));
            Assertions.assertEquals(-1, list.lastIndexOf(badValue));
        }

        @Test
        @Order(5)
        @DisplayName("toArray() and toArray(T[]) return snapshot arrays")
        void toArrayVariants() {
            SJArrayList<String> list = new SJArrayList<>(List.of("a", "b", "c"));
            Object[] arr1 = list.toArray();
            Assertions.assertArrayEquals(new Object[] {"a", "b", "c"}, arr1);

            String[] target = new String[0];
            String[] arr2 = list.toArray(target);
            Assertions.assertArrayEquals(new String[] {"a", "b", "c"}, arr2);

            final int biggerLength = 5;
            String[] bigger = new String[biggerLength];
            Arrays.fill(bigger, "?");
            String[] arr3 = list.toArray(bigger);
            Assertions.assertSame(bigger, arr3,
                    "List should return the provided array when large enough");
            Assertions.assertEquals("a", arr3[0]);
            Assertions.assertEquals("b", arr3[1]);
            Assertions.assertEquals("c", arr3[2]);
            Assertions.assertNull(arr3[3],
                    "Element immediately after the last should be null per spec");
        }

        @Test
        @Order(6)
        @DisplayName("remove(Object) removes first occurrence and returns boolean")
        void removeObject() {
            SJArrayList<String> list = new SJArrayList<>(List.of("a", "b", "a"));
            Assertions.assertTrue(list.remove("a"));
            Assertions.assertIterableEquals(List.of("b", "a"), list);
            Assertions.assertFalse(list.remove("x"));
        }

        @Test
        @Order(7)
        @DisplayName("remove(index) returns removed element and shifts left")
        void removeIndex() {
            final Integer[] values = {10, 20, 30};
            SJArrayList<Integer> list = new SJArrayList<>(Arrays.asList(values));
            int removed = list.remove(1);
            Assertions.assertEquals(values[1], removed);
            Assertions.assertIterableEquals(List.of(values[0], values[2]), list);
        }

        @Test
        @Order(8)
        @DisplayName("clear() empties the list")
        void clearEmpties() {
            SJArrayList<Double> list = new SJArrayList<>(List.of(1.0, 2.0));
            list.clear();
            Assertions.assertTrue(list.isEmpty());
            Assertions.assertEquals(0, list.size());
        }
    }

    @Nested
    @Order(3)
    @DisplayName("Bulk Operations")
    @TestMethodOrder(OrderAnnotation.class)
    class BulkOperations {
        @Test
        @Order(1)
        @DisplayName("addAll(Collection) appends all; addAll(index, Collection) inserts at index")
        void addAllVariants() {
            SJArrayList<String> list = new SJArrayList<>(List.of("A"));
            Assertions.assertTrue(list.addAll(List.of("B", "C")));
            Assertions.assertIterableEquals(List.of("A", "B", "C"), list);
            Assertions.assertTrue(list.addAll(1, List.of("x", "y")));
            Assertions.assertIterableEquals(List.of("A", "x", "y", "B", "C"), list);
        }

        @Test
        @Order(2)
        @DisplayName("containsAll, removeAll, retainAll behave correctly")
        void containsRemoveRetain() {
            final Integer[] values = {1, 2, 3, 4, 5};
            final int badValue = 9;
            SJArrayList<Integer> list = new SJArrayList<>(Arrays.asList(values));
            Assertions.assertTrue(list.containsAll(List.of(2, 4)));
            Assertions.assertTrue(list.removeAll(List.of(1, values[4], badValue))); // 9 not present
            Assertions.assertIterableEquals(List.of(2, 3, 4), list);
            Assertions.assertTrue(list.retainAll(Set.of(2, 4))); // keep only 2,4
            Assertions.assertIterableEquals(List.of(2, 4), list);
        }
    }

    @Nested
    @Order(4)
    @DisplayName("Iterators")
    @TestMethodOrder(OrderAnnotation.class)
    class IteratorTests {
        @Test
        @Order(1)
        @DisplayName("iterator() traverses in order")
        void iteratorOrder() {
            SJArrayList<String> list = new SJArrayList<>(List.of("a", "b", "c"));
            Iterator<String> it = list.iterator();
            List<String> seen = new ArrayList<>();
            while (it.hasNext()) {
                seen.add(it.next());
            }
            Assertions.assertEquals(List.of("a", "b", "c"), seen);
        }

        @Test
        @Order(2)
        @DisplayName("iterator.remove() removes last returned element")
        void iteratorRemove() {
            SJArrayList<Integer> list = new SJArrayList<>(List.of(1, 2, 3));
            Iterator<Integer> it = list.iterator();
            Assertions.assertEquals(1, it.next());
            it.remove(); // remove 1
            Assertions.assertIterableEquals(List.of(2, 3), list);
            Assertions.assertEquals(2, it.next());
            it.remove(); // remove 2
            Assertions.assertIterableEquals(List.of(3), list);
        }

        @Test
        @Order(3)
        @DisplayName("Calling next() past end throws NoSuchElementException")
        void iteratorNextPastEnd() {
            SJArrayList<String> list = new SJArrayList<>(List.of("x"));
            Iterator<String> it = list.iterator();
            Assertions.assertEquals("x", it.next());
            Assertions.assertThrows(NoSuchElementException.class, it::next);
        }

        @Test
        @Order(4)
        @DisplayName("Calling remove() before next() throws IllegalStateException")
        void iteratorRemoveBeforeNext() {
            SJArrayList<String> list = new SJArrayList<>(List.of("x", "y"));
            Iterator<String> it = list.iterator();
            Assertions.assertThrows(IllegalStateException.class, it::remove);
        }
    }

    @Nested
    @Tag("COMMIT5")
    @DisplayName("ListIterator behavior")
    @TestMethodOrder(OrderAnnotation.class)
    class ListIteratorTests {
        @Test
        @Order(1)
        @DisplayName("listIterator(index) supports bidirectional traversal")
        void bidirectionalTraversal() {
            SJArrayList<String> list = new SJArrayList<>(List.of("a", "b", "c"));
            ListIterator<String> it = list.listIterator(1); // at "b"
            Assertions.assertTrue(it.hasPrevious());
            Assertions.assertEquals("b", it.next());   // move to "b"
            Assertions.assertEquals(2, it.nextIndex());
            Assertions.assertEquals(1, it.previousIndex());
            Assertions.assertEquals("b", it.previous()); // back to "b"
            Assertions.assertEquals("b", it.next());     // forward again
            Assertions.assertEquals("c", it.next());     // to end
            Assertions.assertFalse(it.hasNext());
        }

        @Test
        @Order(2)
        @DisplayName("listIterator.add inserts at cursor; set replaces last returned")
        void addAndSet() {
            SJArrayList<Integer> list = new SJArrayList<>(List.of(1, 3));
            ListIterator<Integer> it = list.listIterator(1);
            it.add(2); // insert before element at index 1
            Assertions.assertIterableEquals(List.of(1, 2, 3), list);

            Assertions.assertEquals(2, it.previous()); // move back to 2
            Assertions.assertEquals(2, it.next());     // and forward to make it last returned
            it.set(4);
            Assertions.assertIterableEquals(List.of(1, 4, 3), list);
        }

        @Test
        @Order(3)
        @DisplayName("listIterator.set without valid lastReturned throws IllegalStateException")
        void setWithoutNextOrPrevious() {
            SJArrayList<String> list = new SJArrayList<>(List.of("a"));
            ListIterator<String> it = list.listIterator();
            Assertions.assertThrows(IllegalStateException.class, () -> it.set("x"));
        }
    }

    @Nested
    @Order(6)
    @DisplayName("Equality, hashCode, clone")
    @TestMethodOrder(OrderAnnotation.class)
    class EqualityAndClone {

        @Test
        @Order(1)
        @DisplayName("equals is reflexive/symmetric and compares contents")
        void equalsContract() {
            SJArrayList<String> a = new SJArrayList<>(List.of("x", "y"));
            SJArrayList<String> b = new SJArrayList<>(List.of("x", "y"));
            SJArrayList<String> c = new SJArrayList<>(List.of("x", "z"));
            Assertions.assertAll(
                    () -> Assertions.assertEquals(a, a),
                    () -> Assertions.assertEquals(a, b),
                    () -> Assertions.assertEquals(b, a),
                    () -> Assertions.assertNotEquals(a, c),
                    () -> Assertions.assertNotEquals(null, a),
                    () -> Assertions.assertNotEquals("not a list", a)
            );
        }

        @Test
        @Order(2)
        @DisplayName("hashCode consistent with equals")
        void hashCodeConsistent() {
            SJArrayList<Integer> a = new SJArrayList<>(List.of(1, 2, 3));
            SJArrayList<Integer> b = new SJArrayList<>(List.of(1, 2, 3));
            Assertions.assertEquals(a.hashCode(), b.hashCode());
        }

        @Test
        @Order(3)
        @DisplayName("clone creates a shallow copy with equal contents")
        void cloneCreatesCopy() {
            SJArrayList<StringBuilder> a = new SJArrayList<>(
                    List.of(new StringBuilder("a"), new StringBuilder("b")));
            Object copyObj = a.clone();
            Assertions.assertInstanceOf(SJArrayList.class, copyObj);
            @SuppressWarnings("unchecked")
            SJArrayList<StringBuilder> b = (SJArrayList<StringBuilder>) copyObj;
            Assertions.assertEquals(a, b);
            Assertions.assertNotSame(a, b);
            // shallow clone: element references are the same
            Assertions.assertSame(a.getFirst(), b.getFirst());
        }
    }

    @Nested
    @Order(7)
    @DisplayName("Range & capacity operations")
    @TestMethodOrder(OrderAnnotation.class)
    class RangeAndCapacity {
        @Test
        @Order(1)
        @DisplayName("removeRange removes a contiguous block")
        void removeRangeWorks() {
            final int upperRange = 5;
            final Integer[] values = {0, 1, 2, 3, 4, 5};
            SJArrayList<Integer> list = new SJArrayList<>(Arrays.asList(values));
            list.removeRange(2, upperRange); // remove indices 2,3,4
            Assertions.assertIterableEquals(List.of(0, 1, upperRange), list);
        }

        @Test
        @Order(2)
        @DisplayName("ensureCapacity does not alter contents and allows growth")
        void ensureCapacityAllowsGrowth() {
            final int capacity = 64;
            SJArrayList<String> list = new SJArrayList<>(List.of("a", "b"));
            list.ensureCapacity(capacity);
            list.addAll(List.of("c", "d", "e"));
            Assertions.assertIterableEquals(List.of("a", "b", "c", "d", "e"), list);
        }

        @Test
        @Order(3)
        @DisplayName("trimToSize does not change externally observable behavior")
        void trimToSizeNoBehaviorChange() {
            SJArrayList<Integer> list = new SJArrayList<>(List.of(1, 2, 3, 4));
            list.removeFirst();
            list.trimToSize();
            Assertions.assertIterableEquals(List.of(2, 3, 4), list);
            list.add(1);
            Assertions.assertIterableEquals(List.of(2, 3, 4, 1), list);
        }
    }

    @Nested
    @Order(8)
    @DisplayName("Error & boundary conditions")
    @TestMethodOrder(OrderAnnotation.class)
    class ErrorAndBoundary {
        @Test
        @Order(1)
        @DisplayName("get/set/remove with invalid indices throw IndexOutOfBoundsException")
        void indexOutOfBounds() {
            SJArrayList<String> list = new SJArrayList<>(List.of("a"));
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.set(2, "x"));
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));
        }

        @Test
        @Order(2)
        @DisplayName("listIterator previous() at start throws NoSuchElementException")
        void listIteratorPreviousAtStart() {
            SJArrayList<Integer> list = new SJArrayList<>(List.of(1));
            ListIterator<Integer> it = list.listIterator(0);
            Assertions.assertThrows(NoSuchElementException.class, it::previous);
        }

        @Test
        @Order(3)
        @DisplayName("removeRange with invalid bounds throws IndexOutOfBoundsException")
        void removeRangeInvalid() {
            SJArrayList<Integer> list = new SJArrayList<>(List.of(1, 2, 3));
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.removeRange(-1, 2));
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.removeRange(0, 4));
            Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.removeRange(2, 1));
        }
    }
}
