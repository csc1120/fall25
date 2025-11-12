/*
 * Course: CSC-1120
 * ASSIGNMENT
 * CLASS
 * Name: Sean Jones
 * Last Updated:
 */
package week11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Unit Tests for HashMapChaining
 */
@SuppressWarnings("ConstantConditions")
public class HashMapChainingTest {
    @Nested
    class SizeTests {
        @Test
        @DisplayName("Testing size")

        void sizeReflectsNumberOfKeys() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            Assertions.assertEquals(0, map.size());
            map.put("a", 1);
            map.put("b", 2);
            Assertions.assertEquals(2, map.size());
            // updating existing key should not change size
            map.put("a", 3);
            Assertions.assertEquals(2, map.size());
            map.remove("a");
            Assertions.assertEquals(1, map.size());
            map.clear();
            Assertions.assertEquals(0, map.size());
        }
    }

    @Nested
    class IsEmptyTests {
        @Test
        void isEmptyReflectsState() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            Assertions.assertTrue(map.isEmpty());
            map.put("x", 4);
            Assertions.assertFalse(map.isEmpty());
            map.remove("x");
            Assertions.assertTrue(map.isEmpty());
        }
    }

    @Nested
    class ContainsKeyTests {
        @Test
        void containsKeyFindsExistingKeys() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            map.put("k1", 1);
            map.put("k2", 2);
            Assertions.assertTrue(map.containsKey("k1"));
            Assertions.assertTrue(map.containsKey("k2"));
            Assertions.assertFalse(map.containsKey("k3"));
        }

        @Test
        void containsKeyHandlesNullKey() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            map.put("a", 1);
            Assertions.assertFalse(map.containsKey(null));
        }
    }

    @Nested
    class ContainsValueTests {
        @Test
        void containsValueFindsExistingValues() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            map.put("a", 1);
            map.put("b", 2);
            map.put("c", 1);
            Assertions.assertTrue(map.containsValue(1));
            Assertions.assertTrue(map.containsValue(2));
            Assertions.assertFalse(map.containsValue(3));
        }

        @Test
        void containsValueHandlesNullValues() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            map.put("a", null);
            Assertions.assertTrue(map.containsValue(null));
            map.put("b", 4);
            Assertions.assertTrue(map.containsValue(null));
        }
    }

    @Nested
    class GetTests {
        @Test
        void getReturnsMappedValue() {
            HashMapChaining<String, String> map = new HashMapChaining<>();
            map.put("one", "1");
            map.put("two", "2");
            Assertions.assertEquals("1", map.get("one"));
            Assertions.assertEquals("2", map.get("two"));
            Assertions.assertNull(map.get("three"));
        }

        @Test
        void getHandlesNullKey() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            map.put("a", 1);
            Assertions.assertNull(map.get(null));
        }
    }

    @Nested
    class PutTests {
        @Test
        void putInsertsNewKeyAndReturnsNull() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            Assertions.assertNull(map.put("x", 4));
            Assertions.assertEquals(Integer.valueOf(4), map.get("x"));
            Assertions.assertEquals(1, map.size());
        }

        @Test
        void putReplacesValueAndReturnsOldValue() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            map.put("y", 4);
            Assertions.assertEquals(Integer.valueOf(4), map.put("y", 2));
            Assertions.assertEquals(Integer.valueOf(2), map.get("y"));
            Assertions.assertEquals(1, map.size());
        }

        @Test
        void putTriggersRehashAndKeepsEntries() {
            HashMapChaining<Integer, Integer> map = new HashMapChaining<>();
            // add enough entries to exceed load factor threshold and trigger rehash
            final int initial = 30; // 30 > 11 * 2.5 is enough to rehash
            for (int i = 0; i < initial; i++) {
                map.put(i, i * 2);
            }
            Assertions.assertEquals(initial, map.size());
            for (int i = 0; i < initial; i++) {
                Assertions.assertEquals(Integer.valueOf(i * 2), map.get(i));
            }
        }
    }

    @Nested
    class RemoveTests {
        @Test
        void removeDeletesKeyAndReturnsValue() {
            HashMapChaining<String, String> map = new HashMapChaining<>();
            map.put("a", "alpha");
            map.put("b", "beta");
            Assertions.assertEquals("alpha", map.remove("a"));
            Assertions.assertNull(map.get("a"));
            Assertions.assertEquals(1, map.size());
            // removing non-existent key returns null
            Assertions.assertNull(map.remove("c"));
        }

        @Test
        void removeHandlesNullKey() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            map.put("a", 1);
            Assertions.assertNull(map.remove(null));
        }
    }

    @Nested
    class PutAllTests {
        @Test
        void putAllCopiesMappingsFromOtherMap() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            Map<String, Integer> src = new HashMap<>();
            src.put("one", 1);
            src.put("two", 2);
            map.putAll(src);
            Assertions.assertTrue(map.containsKey("one"));
            Assertions.assertTrue(map.containsKey("two"));
            Assertions.assertEquals(Integer.valueOf(1), map.get("one"));
            Assertions.assertEquals(Integer.valueOf(2), map.get("two"));
            // putAll with null map should do nothing
            map.putAll(null);
            Assertions.assertEquals(2, map.size());
        }
    }

    @Nested
    class ClearTests {
        @Test
        void clearEmptiesMap() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            map.put("a", 1);
            map.put("b", 2);
            map.clear();
            Assertions.assertEquals(0, map.size());
            Assertions.assertTrue(map.isEmpty());
        }
    }

    @Nested
    class KeySetTests {
        @Test
        void keySetReturnsAllKeys() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            map.put("x", 1);
            map.put("y", 2);
            map.put("z", 3);
            Set<String> keys = map.keySet();
            Assertions.assertEquals(3, keys.size());
            Assertions.assertTrue(keys.contains("x"));
            Assertions.assertTrue(keys.contains("y"));
            Assertions.assertTrue(keys.contains("z"));
        }
    }

    @Nested
    class ValuesTests {
        @Test
        void valuesReturnsAllValues() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            map.put("a", 1);
            map.put("b", 2);
            map.put("c", 1);
            Collection<Integer> values = map.values();
            Assertions.assertEquals(3, values.size());
            Assertions.assertTrue(values.contains(1));
            Assertions.assertTrue(values.contains(2));
        }
    }

    @Nested
    class EntrySetTests {
        @Test
        void entrySetContainsAllEntries() {
            HashMapChaining<String, Integer> map = new HashMapChaining<>();
            map.put("a", 1);
            map.put("b", 2);
            Set<Map.Entry<String, Integer>> entries = map.entrySet();
            Assertions.assertEquals(2, entries.size());
            for (Map.Entry<String, Integer> e : entries) {
                if ("a".equals(e.getKey())) {
                    Assertions.assertEquals(Integer.valueOf(1), e.getValue());
                } else if ("b".equals(e.getKey())) {
                    Assertions.assertEquals(Integer.valueOf(2), e.getValue());
                } else {
                    Assertions.fail("Unexpected key in entry set: " + e.getKey());
                }
            }
        }
    }
}
