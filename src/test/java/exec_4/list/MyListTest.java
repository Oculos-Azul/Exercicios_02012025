package exec_4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estruturascollections.MyList;

class MyListTest {
	private MyList<Integer> list;

	@BeforeEach
	void setUp() {
		list = new MyList<>();
	}

	@Test
	void testAddSuccess() {
		assertTrue(list.add(5));
		assertEquals(1, list.size());
	}

	@Test
	void testAddWithGrowth() {
		list.add(1);
		list.add(2);

		assertEquals(2, list.size());

		for (int count = 3; count < 15; count++) {
			list.add(count);
		}

		assertEquals(14, list.size());
		assertEquals(3, list.get(2));
	}

	@Test
	void testIsEmptySucesso() {
		assertTrue(list.isEmpty());
	}

	@Test
	void testIsEmptyFailure() {
		list.add(5);
		assertFalse(list.isEmpty());
	}

	@Test
	void testGrowMethod() {
		list.add(1);
		list.add(2);

		int initialCapacity = list.toArray().length;

		list.grow(10);

		int newCapacity = list.toArray().length;

		assertEquals(initialCapacity, newCapacity);
	}

	@Test
	void testContainsSuccess() {
		list.add(5);
		assertTrue(list.contains(5));
	}

	@Test
	void testContainsFailure() {
		list.add(10);
		assertFalse(list.contains(5));
	}

	@Test
	void testRemoveSuccess() {
		list.add(10);
		assertTrue(list.remove(Integer.valueOf(10)));
		assertEquals(0, list.size());
	}

	@Test
	void testRemoveFailure() {
		list.add(5);
		assertFalse(list.remove(Integer.valueOf(10)));
	}

	@Test
	void testGetSuccess() {
		list.add(15);
		assertEquals(15, list.get(0));
	}

	@Test
	void testGetFailure() {
		list.add(1);
		list.add(2);

		assertNull(list.get(3));
		assertNull(list.get(-1));
	}

	@Test
	void testSetSuccess() {
		list.add(10);
		assertEquals(10, list.set(0, 20));
		assertEquals(20, list.get(0));
	}

	@Test
	void testSetFailure() {
		list.add(1);
		list.add(2);

		assertNull(list.set(3, 10));
		assertNull(list.set(-1, 10));

		assertEquals(2, list.size());
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
	}

	@Test
	void testIteratorSuccess() {
		list.add(1);
		list.add(2);
		Iterator<Integer> it = list.iterator();
		assertTrue(it.hasNext());
		assertEquals(1, it.next());
		assertTrue(it.hasNext());
		assertEquals(2, it.next());
	}

	@Test
	void testIteratorFailure() {
		Iterator<Integer> it = list.iterator();
		assertFalse(it.hasNext());
	}

	@Test
	void testSubListSuccess() {
		list.add(1);
		list.add(2);
		list.add(3);
		MyList<Integer> subList = (MyList<Integer>) list.subList(0, 2);
		assertEquals(2, subList.size());
		assertEquals(1, subList.get(0));
		assertEquals(2, subList.get(1));
	}

	@Test
	void testSubListFailure() {
		list.add(1);
		list.add(2);
		list.add(3);

		MyList<Integer> invalidSubList = list.subList(-1, 5);
		assertTrue(invalidSubList.isEmpty());

		invalidSubList = list.subList(2, 1);
		assertTrue(invalidSubList.isEmpty());

		assertEquals(3, list.size());
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
	}

	@Test
	void testSubListInvalidIndexes() {
		list.add(1);
		list.add(2);
		list.add(3);

		MyList<Integer> subList1 = list.subList(-1, 2);
		assertTrue(subList1.isEmpty());

		MyList<Integer> subList2 = list.subList(0, 5);
		assertTrue(subList2.isEmpty());

		MyList<Integer> subList3 = list.subList(2, 1);
		assertTrue(subList3.isEmpty());
	}

	@Test
	void testClear() {
		list.add(10);
		list.add(20);
		list.clear();
		assertEquals(0, list.size());
	}

	@Test
	void testAddAllSuccess() {
		MyList<Integer> otherList = new MyList<>();
		otherList.add(1);
		otherList.add(2);
		list.addAll(Arrays.asList(1, 2, 3));
		assertTrue(list.containsAll(Arrays.asList(1, 2, 3)));
	}

	@Test
	void testAddAllAtIndexSuccess() {
		list.add(1);
		list.add(4);
		list.addAll(1, Arrays.asList(2, 3));
		assertEquals(4, list.size());
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
		assertEquals(4, list.get(3));
	}

	@Test
	void testAddAllAtIndexFailure() {
		assertFalse(list.addAll(-1, Arrays.asList(1, 2, 3)));
		assertFalse(list.addAll(1, Arrays.asList(1, 2, 3)));
		assertFalse(list.containsAll(Arrays.asList(1, 2, 3)));

		assertEquals(0, list.size());
	}

	@Test
	void testRemoveAllSuccess() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		boolean result = list.removeAll(Arrays.asList(2, 4));
		assertTrue(result);
		assertEquals(2, list.size());
		assertEquals(1, list.get(0));
		assertEquals(3, list.get(1));
	}

	@Test
	void testRemoveAllNoMatch() {
		list.add(1);
		list.add(2);
		list.add(3);

		boolean result = list.removeAll(Arrays.asList(4, 5));
		assertFalse(result);
		assertEquals(3, list.size());
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
	}

	@Test
	void testRemoveAllEmptyCollection() {
		list.add(1);
		list.add(2);

		boolean result = list.removeAll(Arrays.asList());
		assertFalse(result);
		assertEquals(2, list.size());
	}

	@Test
	void testRetainAllSuccess() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);

		boolean result = list.retainAll(Arrays.asList(2, 4));
		assertTrue(result);
		assertEquals(2, list.size());
		assertEquals(2, list.get(0));
		assertEquals(4, list.get(1));
	}

	@Test
	void testRetainAllNoMatch() {
		list.add(1);
		list.add(2);
		list.add(3);

		boolean result = list.retainAll(Arrays.asList(4, 5));
		assertTrue(result);
		assertEquals(0, list.size());
	}

	@Test
	void testRetainAllNoChange() {
		list.add(1);
		list.add(2);

		boolean result = list.retainAll(Arrays.asList(1, 2));
		assertFalse(result);
		assertEquals(2, list.size());
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
	}

	@Test
	void testAddAtIndexSuccess() {
		list.add(0, 1);
		assertEquals(1, list.size());
		assertEquals(1, list.get(0));

		list.add(1, 2);
		assertEquals(2, list.size());
		assertEquals(2, list.get(1));

		list.add(1, 3);
		assertEquals(3, list.size());
		assertEquals(3, list.get(1));
		assertEquals(2, list.get(2));
	}

	@Test
	void testAddWithInvalidIndex() {
		list.add(1);
		list.add(2);
		list.add(3);

		list.add(-1, 4);
		assertEquals(4, list.size());
		assertEquals(4, list.get(0));

		list.add(10, 5);
		assertEquals(5, list.size());
		assertEquals(5, list.get(4));
	}

	@Test
	void testIndexOfSuccess() {
		list.add(1);
		list.add(2);
		list.add(3);

		assertEquals(1, list.indexOf(2));
		assertEquals(0, list.indexOf(1));
		assertEquals(2, list.indexOf(3));
	}

	@Test
	void testIndexOfFailure() {
		list.add(1);
		list.add(2);

		assertEquals(-1, list.indexOf(3));
	}

	@Test
	void testLastIndexOfSuccess() {
		list.add(1);
		list.add(2);
		list.add(2);
		list.add(3);

		assertEquals(2, list.lastIndexOf(2));
		assertEquals(3, list.lastIndexOf(3));
	}

	@Test
	void testLastIndexOfFailure() {
		list.add(1);
		list.add(2);

		assertEquals(-1, list.lastIndexOf(3));
	}

	@Test
	void testToArray() {
		list.add(1);
		list.add(2);
		list.add(3);

		Object[] array = list.toArray();

		assertNotNull(array);
		assertEquals(3, array.length);
		assertEquals(1, array[0]);
		assertEquals(2, array[1]);
		assertEquals(3, array[2]);
	}

	@Test
	void testToArrayWhenEmpty() {
		Object[] array = list.toArray();

		assertNotNull(array);
		assertEquals(0, array.length);
	}

	@Test
	void testGrowMethodCalledFromAdd() {
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

		list.add(10, 49);

		int newCapacity = list.toArray().length;

		assertEquals(11, newCapacity);
	}

	@Test
	void testRemoveIndexSuccess() {
		list.add(1);
		list.add(2);
		list.add(3);

		int initialSize = list.size();

		Integer removedItem = list.remove(1);

		assertEquals(Integer.valueOf(2), removedItem);
		assertEquals(initialSize - 1, list.size());
		assertEquals(Integer.valueOf(1), list.get(0));
		assertEquals(Integer.valueOf(3), list.get(1));
	}

	@Test
	void testRemoveFail() {
		list.add(1);
		list.add(2);

		Integer removedItem1 = list.remove(-1);
		Integer removedItem2 = list.remove(2);

		assertNull(removedItem1);
		assertNull(removedItem2);
	}

	@Test
	void testListIteratorSuccess() {
		list.add(1);
		list.add(2);
		list.add(3);

		ListIterator<Integer> iterator = list.listIterator();

		assertTrue(iterator.hasNext());
		assertEquals(Integer.valueOf(1), iterator.next());
		assertEquals(Integer.valueOf(2), iterator.next());
		assertEquals(Integer.valueOf(3), iterator.next());
		assertFalse(iterator.hasNext());
	}

	@Test
	void testListIteratorPreviousSuccess() {
		list.add(1);
		list.add(2);
		list.add(3);

		ListIterator<Integer> iterator = list.listIterator();

		iterator.next();
		iterator.next();
		iterator.next();

		assertTrue(iterator.hasPrevious());
		assertEquals(Integer.valueOf(3), iterator.previous());
		assertEquals(Integer.valueOf(2), iterator.previous());
		assertEquals(Integer.valueOf(1), iterator.previous());
		assertFalse(iterator.hasPrevious());
		iterator.add(1);
		iterator.set(1);
		assertEquals(Integer.valueOf(0), iterator.previousIndex());
		assertEquals(Integer.valueOf(1), iterator.nextIndex());

	}

	@Test
	void testListIteratorFail() {
		list.add(1);

		ListIterator<Integer> iterator = list.listIterator();

		iterator.next();
		assertNull(iterator.next());
		iterator.remove();
		iterator.set(2);
		assertNull(iterator.previous());
	}

	@Test
	void testToArraySuccess() {
		list.add(1);
		list.add(2);
		list.add(3);

		Integer[] array = new Integer[5];
		Integer[] result = list.toArray(array);

		assertEquals(5, result.length);
		assertEquals(Integer.valueOf(1), result[0]);
		assertEquals(Integer.valueOf(2), result[1]);
		assertEquals(Integer.valueOf(3), result[2]);
		assertNull(result[3]);
		assertNull(result[4]);
	}

	@Test
	void testToArrayWithInsufficientSize() {
		list.add(1);
		list.add(2);
		list.add(3);

		Integer[] array = new Integer[2];
		Integer[] result = list.toArray(array);

		assertEquals(3, result.length);
		assertEquals(Integer.valueOf(1), result[0]);
		assertEquals(Integer.valueOf(2), result[1]);
		assertEquals(Integer.valueOf(3), result[2]);
	}

	@Test
	void testListIteratorIndexSuccess() {
		list.add(10);
		list.add(20);
		list.add(30);

		ListIterator<Integer> iterator = list.listIterator(1);

		assertTrue(iterator.hasNext());
		assertEquals(Integer.valueOf(20), iterator.next());

		assertTrue(iterator.hasPrevious());
		assertEquals(Integer.valueOf(2), iterator.nextIndex());

		iterator.add(25);
		assertTrue(iterator.hasPrevious());
		assertEquals(Integer.valueOf(25), iterator.previous());

		iterator.set(15);
		assertEquals(Integer.valueOf(15), iterator.previous());

		iterator.next();
		iterator.next();
		iterator.next();
		assertNull(iterator.next());
	}

	@Test
	void testListIteratorFailure() {
		list.add(10);
		list.add(20);

		ListIterator<Integer> invalidIterator = list.listIterator(3);
		assertFalse(invalidIterator.hasNext());
		assertNull(invalidIterator.next());
		assertNull(invalidIterator.previous());

		ListIterator<Integer> iterator = list.listIterator(0);
		assertFalse(iterator.hasPrevious());
		assertNull(iterator.previous());

		iterator.next();
		iterator.remove();
		iterator.previousIndex();
		assertEquals(1, list.size());

		iterator.remove();
		assertEquals(1, list.size());

		iterator.set(30);
		assertEquals(20, list.get(0));

		iterator.add(40);
		assertEquals(2, list.size());
		assertEquals(40, list.get(0));
	}
}