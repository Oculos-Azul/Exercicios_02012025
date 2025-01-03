package exec_4.set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import estruturascollections.MySet;

class MySetTest {

    @Test
    void testAddSuccess() {
        MySet<Integer> set = new MySet<>();
        assertTrue(set.add(10), "Should add element successfully");
    }

    @Test
    void testAddFail() {
        MySet<Integer> set = new MySet<>();
        set.add(10);
        assertFalse(set.add(10), "Should not add duplicate element");
    }

    @Test
    void testContainsSuccess() {
        MySet<String> set = new MySet<>();
        set.add("Hello");
        assertTrue(set.contains("Hello"), "Set should contain the element");
    }

    @Test
    void testContainsFail() {
        MySet<String> set = new MySet<>();
        set.add("Hello");
        assertFalse(set.contains("World"), "Set should not contain the element");
    }

    @Test
    void testUpdateSuccess() {
        MySet<Double> set = new MySet<>();
        set.add(1.1);
        assertTrue(set.update(1.1, 2.2), "Should update element successfully");
        assertTrue(set.contains(2.2), "Set should contain the updated element");
    }

    @Test
    void testUpdateFail() {
        MySet<Double> set = new MySet<>();
        set.add(1.1);
        assertFalse(set.update(2.2, 3.3), "Should fail to update non-existent element");
    }

    @Test
    void testRemoveSuccess() {
        MySet<Character> set = new MySet<>();
        set.add('A');
        assertTrue(set.remove('A'), "Should remove element successfully");
        assertFalse(set.contains('A'), "Set should not contain the removed element");
    }

    @Test
    void testRemoveFail() {
        MySet<Character> set = new MySet<>();
        assertFalse(set.remove('B'), "Should fail to remove non-existent element");
    }

    @Test
    void testSortNaturalOrder() {
        MySet<Integer> set = new MySet<>();
        set.add(3);
        set.add(1);
        set.add(2);
        set.sort();
        Object[] sorted = set.toArray();
        assertArrayEquals(new Integer[] { 1, 2, 3 }, sorted, "Set should be sorted in natural order");
    }

    @Test
    void testSortWithComparator() {
        MySet<Integer> set = new MySet<>();
        set.add(3);
        set.add(1);
        set.add(2);
        set.sort(Comparator.reverseOrder());
        Object[] sorted = set.toArray();
        assertArrayEquals(new Integer[] { 3, 2, 1 }, sorted);
    }

    @Test
    void testClear() {
        MySet<String> set = new MySet<>();
        set.add("Test");
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    void testIteratorSuccess() {
        MySet<Integer> set = new MySet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> iterator = set.iterator();

        List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertEquals(2, result.size());
    }

    @Test
    void testGrowMethodCalledFromAdd() {
        MySet<Integer> set = new MySet<>();

        for (int i = 0; i < 10; i++) {
            assertTrue(set.add(i));
        }

        int initialCapacity = set.getElementsCapacity();

        assertTrue(set.add(10));

        int newCapacity = set.getElementsCapacity();
        assertTrue(newCapacity > initialCapacity);

        assertEquals(11, set.size());
    }

    @Test
    void testRemoveMiddleElement() {
        MySet<Integer> set = new MySet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        assertEquals(3, set.size());
        assertTrue(set.remove(2));
        assertEquals(2, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(3));
        assertFalse(set.contains(2));
    }

    @Test
    void testIteratorFail() {
        MySet<Integer> set = new MySet<>();
        Iterator<Integer> iterator = set.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIsEmptySuccess() {
        MySet<Integer> set = new MySet<>();
        assertTrue(set.isEmpty());

        set.add(1);
        assertFalse(set.isEmpty());
    }

    @Test
    void testNextThrowsNoSuchElementException() {
        MySet<Integer> set = new MySet<>();
        set.add(1);
        set.add(2);

        Iterator<Integer> iterator = set.iterator();

        iterator.next();
        iterator.next();

        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    void testPrintAllSuccess() {
        MySet<Integer> set = new MySet<>();
        set.add(1);
        set.add(2);

        String output = capturePrintAllOutput(set);

        assertTrue(output.contains("1"));
        assertTrue(output.contains("2"));
    }

    @Test
    void testPrintAllFail() {
        MySet<Integer> set = new MySet<>();

        String output = capturePrintAllOutput(set);

        assertTrue(output.isEmpty());
    }

    private String capturePrintAllOutput(MySet<Integer> set) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        set.printAll();

        System.setOut(System.out);
        return outputStream.toString().trim();
    }
}
