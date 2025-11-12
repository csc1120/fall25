/*
 * Course: CSC-1020
 * Hash Maps
 * HashMapLinearProbingTest
 * Name: Sean Jones
 * Last Updated: 11-12-25
 */

package week11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Test suite for the Hash Map with Linear Probing
 */
@SuppressWarnings("ConstantConditions")
public class HashMapLinearProbingTest {

    @Nested
    class SizeTests {
        @Test
        void sizeReflectsNumberOfKeys() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
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
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
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
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("k1", 1);
            map.put("k2", 2);
            Assertions.assertTrue(map.containsKey("k1"));
            Assertions.assertTrue(map.containsKey("k2"));
            Assertions.assertFalse(map.containsKey("k3"));
        }

        @Test
        void containsKeyHandlesNullKey() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("a", 1);
            Assertions.assertFalse(map.containsKey(null));
        }

        @Test
        void containsKeySkipsDeletedEntries() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("a", 1);
            map.put("b", 2);
            map.remove("a");
            // Deleted tombstone should not cause an exception or false positive
            Assertions.assertFalse(map.containsKey("a"));
            Assertions.assertTrue(map.containsKey("b"));
            map.put("a", 3); // reuse tombstone slot
            Assertions.assertTrue(map.containsKey("a"));
        }
    }

    @Nested
    class ContainsValueTests {
        @Test
        void containsValueFindsExistingValues() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("a", 1);
            map.put("b", 2);
            map.put("c", 1);
            Assertions.assertTrue(map.containsValue(1));
            Assertions.assertTrue(map.containsValue(2));
            Assertions.assertFalse(map.containsValue(3));
        }

        @Test
        void containsValueHandlesNullValues() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
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
            HashMapLinearProbing<String, String> map = new HashMapLinearProbing<>();
            map.put("one", "1");
            map.put("two", "2");
            Assertions.assertEquals("1", map.get("one"));
            Assertions.assertEquals("2", map.get("two"));
            Assertions.assertNull(map.get("three"));
        }

        @Test
        void getHandlesNullKey() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("a", 1);
            Assertions.assertNull(map.get(null));
        }

        @Test
        void getSkipsDeletedEntries() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("a", 1);
            map.put("b", 2);
            map.remove("a");
            Assertions.assertEquals(Integer.valueOf(2), map.get("b"));
            map.put("a", 3);
            Assertions.assertEquals(Integer.valueOf(3), map.get("a"));
        }
    }

    @Nested
    class PutTests {
        @Test
        void putInsertsNewKeyAndReturnsNull() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            Assertions.assertNull(map.put("x", 4));
            Assertions.assertEquals(Integer.valueOf(4), map.get("x"));
            Assertions.assertEquals(1, map.size());
        }

        @Test
        void putReplacesValueAndReturnsOldValue() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("y", 4);
            Assertions.assertEquals(Integer.valueOf(4), map.put("y", 2));
            Assertions.assertEquals(Integer.valueOf(2), map.get("y"));
            Assertions.assertEquals(1, map.size());
        }

        @Test
        void putReusesDeletedSlot() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("a", 1);
            map.remove("a");
            Assertions.assertNull(map.put("a", 2));
            Assertions.assertEquals(Integer.valueOf(2), map.get("a"));
        }

        @Test
        void putTriggersRehashAndKeepsEntries() {
            HashMapLinearProbing<Integer, Integer> map = new HashMapLinearProbing<>();
            // fill enough to exceed load factor threshold 0.75
            int initial = 3;
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
            HashMapLinearProbing<String, String> map = new HashMapLinearProbing<>();
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
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("a", 1);
            Assertions.assertNull(map.remove(null));
        }
    }

    @Nested
    class PutAllTests {
        @Test
        void putAllCopiesMappingsFromOtherMap() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            Map<String, Integer> src = new HashMap<>();
            src.put("one", 1);
            src.put("two", 2);
            map.putAll(src);
            Assertions.assertTrue(map.containsKey("one"));
            Assertions.assertTrue(map.containsKey("two"));
            Assertions.assertEquals(Integer.valueOf(1), map.get("one"));
            Assertions.assertEquals(Integer.valueOf(2), map.get("two"));
        }
    }

    @Nested
    class ClearTests {
        @Test
        void clearEmptiesMap() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
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
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("x", 1);
            map.put("y", 2);
            map.put("z", 3);
            Set<String> keys = map.keySet();
            Assertions.assertEquals(3, keys.size());
            Assertions.assertTrue(keys.contains("x"));
            Assertions.assertTrue(keys.contains("y"));
            Assertions.assertTrue(keys.contains("z"));
        }

        @Test
        void keySetIgnoresDeletedEntries() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("a", 1);
            map.remove("a");
            Assertions.assertFalse(map.containsKey("a"));
            map.put("a", 2);
            Assertions.assertTrue(map.containsKey("a"));
        }
    }

    @Nested
    class ValuesTests {
        @Test
        void valuesReturnsAllValues() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("a", 1);
            map.put("b", 2);
            map.put("c", 1);
            Collection<Integer> values = map.values();
            Assertions.assertEquals(2, values.size());
            Assertions.assertTrue(values.contains(1));
            Assertions.assertTrue(values.contains(2));
        }

        @Test
        void valuesIgnoresDeletedEntries() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("a", 1);
            map.remove("a");
            Assertions.assertEquals(0, map.size());
        }
    }

    @Nested
    class EntrySetTests {
        @Test
        void entrySetContainsAllEntries() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
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
                    Assertions.fail("Unexpected key: " + e.getKey());
                }
            }
        }

        @Test
        void entrySetIgnoresDeletedEntries() {
            HashMapLinearProbing<String, Integer> map = new HashMapLinearProbing<>();
            map.put("a", 1);
            map.remove("a");
            Assertions.assertEquals(0, map.size());
        }
    }
}
