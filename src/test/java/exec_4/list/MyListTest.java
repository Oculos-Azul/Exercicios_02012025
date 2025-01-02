package exec_4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estruturascollections.MyList;

public class MyListTest {
    private MyList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new MyList<>();
    }

    @Test
    public void testAddSuccess() {
        assertTrue(list.add(5));
        assertEquals(1, list.size());
    }

    @Test
    public void testContainsSuccess() {
        list.add(5);
        assertTrue(list.contains(5));
    }

    @Test
    public void testContainsFailure() {
        list.add(10);
        assertFalse(list.contains(5));
    }

    @Test
    public void testRemoveSuccess() {
        list.add(10);
        assertTrue(list.remove(Integer.valueOf(10)));
        assertEquals(0, list.size());
    }

    @Test
    public void testRemoveFailure() {
        list.add(5);

        assertFalse(list.remove(Integer.valueOf(10)));
    }

    @Test
    public void testGetSuccess() {
        list.add(15);
        assertEquals(15, list.get(0));
    }

    @Test
    public void testGetFailure() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    public void testSetSuccess() {
        list.add(10);
        assertEquals(10, list.set(0, 20));
        assertEquals(20, list.get(0));
    }

    @Test
    public void testSetFailure() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 10));
    }

    @Test
    public void testIteratorSuccess() {
        list.add(1);
        list.add(2);
        Iterator<Integer> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertTrue(it.hasNext());
        assertEquals(2, it.next());
    }

    @Test
    public void testIteratorFailure() {
        Iterator<Integer> it = list.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void testSubListSuccess() {
        list.add(1);
        list.add(2);
        list.add(3);
        MyList<Integer> subList = (MyList<Integer>) list.subList(0, 2);
        assertEquals(2, subList.size());
        assertEquals(1, subList.get(0));
        assertEquals(2, subList.get(1));
    }

    @Test
    public void testSubListFailure() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.subList(1, 0));
    }

    @Test
    public void testClear() {
        list.add(10);
        list.add(20);
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testAddAllSuccess() {
        MyList<Integer> otherList = new MyList<>();
        otherList.add(1);
        otherList.add(2);
        list.addAll(Arrays.asList(1, 2, 3));
        assertTrue(list.containsAll(Arrays.asList(1, 2, 3)));
    }
}
