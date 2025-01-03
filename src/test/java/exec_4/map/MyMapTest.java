package exec_4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import estruturascollections.MyMap;

class MyMapTest {

    @Test
    void testPutSuccess() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("key1", 1);
        assertEquals(1, map.get("key1"));
    }

    @Test
    void testPutFailNullKey() {
        MyMap<String, Integer> map = new MyMap<>();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            map.put(null, 1);
        });
        assertEquals("A chave não pode ser nula", exception.getMessage());
    }

    @Test
    void testPutExistingKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("key1", 1);
        map.put("key1", 2);

        assertEquals(2, map.get("key1"));
    }

    @Test
    void testPutGrow() {
        MyMap<String, Integer> map = new MyMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, i);
        }

        map.put("key10", 10);

        assertEquals(10, map.get("key10"));
    }

    @Test
    void testGetSuccess() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("key1", 1);
        assertEquals(1, map.get("key1"));
    }

    @Test
    void testGetFailKeyNotFound() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("key1", 1);
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            map.get("key2");
        });
        assertEquals("Chave não encontrada", exception.getMessage());
    }

    @Test
    void testRemoveSuccess() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("key1", 1);
        map.remove("key1");
        assertFalse(map.containsKey("key1"));
    }

    @Test
    void testRemoveFailKeyNotFound() {
        MyMap<String, Integer> map = new MyMap<>();
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            map.remove("key2");
        });
        assertEquals("Chave não encontrada", exception.getMessage());
    }

    @Test
    void testRemoveKey() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);

        map.remove("key2");

        assertEquals(2, map.size());
    }

    @Test
    void testContainsKeySuccess() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("key1", 1);
        map.put("key2", 1);
        assertTrue(map.containsKey("key1"));
    }

    @Test
    void testContainsKeyFail() {
        MyMap<String, Integer> map = new MyMap<>();
        assertFalse(map.containsKey("key2"));
    }

    @Test
    void testSizeSuccess() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        assertEquals(2, map.size());
    }

    @Test
    void testSizeAfterRemove() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("key1", 1);
        map.remove("key1");
        assertEquals(0, map.size());
    }

    @Test
    void testIsEmptySuccess() {
        MyMap<String, Integer> map = new MyMap<>();
        assertTrue(map.isEmpty());
    }

    @Test
    void testIsEmptyAfterPut() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("key1", 1);
        assertFalse(map.isEmpty());
    }

    @Test
    void testClearSuccess() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    void testClearAfterClear() {
        MyMap<String, Integer> map = new MyMap<>();
        map.put("key1", 1);
        map.clear();
        assertFalse(map.containsKey("key1"));
    }
}
