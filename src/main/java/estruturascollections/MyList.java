package estruturascollections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList<T> {
	private Object[] items;
	private int size;

	public MyList() {
		items = new Object[10];
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(Object o) {
		for (int i = 0; i < size; i++) {
			if (o.equals(items[i])) {
				return true;
			}
		}
		return false;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int currentIndex = 0;

			public boolean hasNext() {
				return currentIndex < size;
			}

			public T next() {
				return (T) items[currentIndex++];
			}
		};
	}

	public Object[] toArray() {
		Object[] newArray = new Object[size];
		for (int i = 0; i < size; i++) {
			newArray[i] = items[i];
		}
		return newArray;
	}

	public boolean add(Object e) {
		if (size == items.length) {
			grow();
		}
		items[size++] = e;
		return true;
	}

	private void grow() {
		int newLength = items.length * 2;
		Object[] newItems = new Object[newLength];
		System.arraycopy(items, 0, newItems, 0, size);
		items = newItems;
	}

	public void grow(int numElementsToAdd) {
		int newLength = items.length + numElementsToAdd;
		Object[] newItems = new Object[newLength];
		System.arraycopy(items, 0, newItems, 0, size);
		items = newItems;
	}

	public boolean remove(Object o) {
		for (int i = 0; i < size; i++) {
			if (o.equals(items[i])) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}

	private void removeAt(int index) {
		for (int i = index; i < size - 1; i++) {
			items[i] = items[i + 1];
		}
		items[--size] = null;
	}

	public T get(int index) {
		if (index >= size || index < 0) {
			return null;
		}
		return (T) items[index];
	}

	public T set(int index, Object element) {
		if (index >= size || index < 0) {
			return null;
		}
		Object oldValue = items[index];
		items[index] = element;
		return (T) oldValue;
	}

	public void add(int index, Object element) {
		if (index < 0) {
			index = 0;
		} else if (index > size) {
			index = size;
		}

		if (size == items.length) {
			grow();
		}

		for (int i = size; i > index; i--) {
			items[i] = items[i - 1];
		}

		items[index] = element;
		size++;
	}

	public T remove(int index) {
		if (index >= size || index < 0) {
			return null;
		}
		Object removedItem = items[index];
		removeAt(index);
		return (T) removedItem;
	}

	public int indexOf(Object o) {
		for (int i = 0; i < size; i++) {
			if (o.equals(items[i])) {
				return i;
			}
		}
		return -1;
	}

	public int lastIndexOf(Object o) {
		for (int i = size - 1; i >= 0; i--) {
			if (o.equals(items[i])) {
				return i;
			}
		}
		return -1;
	}

	public ListIterator<T> listIterator() {
		return new ListIterator<T>() {
			private int currentIndex = 0;

			public boolean hasNext() {
				return currentIndex < size;
			}

			public T next() {
				if (!hasNext()) {
					return null;
				}
				return (T) items[currentIndex++];
			}

			public boolean hasPrevious() {
				return currentIndex > 0;
			}

			public T previous() {
				if (!hasPrevious()) {
					return null;
				}
				return (T) items[--currentIndex];
			}

			public int nextIndex() {
				return currentIndex;
			}

			public int previousIndex() {
				return currentIndex - 1;
			}

			public void remove() {
				MyList.this.remove(--currentIndex);
			}

			public void set(T e) {
				MyList.this.set(currentIndex - 1, e);
			}

			public void add(T e) {
				MyList.this.add(currentIndex, e);
				currentIndex++;
			}

		};
	}

	public ListIterator<T> listIterator(int index) {
		return new ListIterator<T>() {
			private int currentIndex = index;

			public boolean hasNext() {
				return currentIndex < size;
			}

			public T next() {
				if (!hasNext()) {
					return null;
				}
				return (T) items[currentIndex++];
			}

			public boolean hasPrevious() {
				return currentIndex > 0;
			}

			public T previous() {
				if (!hasPrevious()) {
					return null;
				}
				return (T) items[--currentIndex];
			}

			public int nextIndex() {
				return currentIndex;
			}

			public int previousIndex() {
				return currentIndex - 1;
			}

			public void remove() {
				MyList.this.remove(--currentIndex);
			}

			public void set(T e) {
				MyList.this.set(currentIndex - 1, e);
			}

			public void add(T e) {
				MyList.this.add(currentIndex++, e);
			}
		};
	}

	public MyList<T> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
			return new MyList<T>();
		}
		MyList<T> subList = new MyList<T>();
		for (int i = fromIndex; i < toIndex; i++) {
			subList.add((T) items[i]);
		}
		return subList;
	}

	public boolean containsAll(Collection<?> c) {
		for (Object element : c) {
			if (!contains(element)) {
				return false;
			}
		}
		return true;
	}

	public boolean addAll(Collection<? extends T> c) {
		boolean modified = false;
		for (Object element : c) {
			add(element);
			modified = true;
		}
		return modified;
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		if (index < 0 || index > size) {
			return false;
		}

		int numElementsToAdd = c.size();

		for (int i = size - 1; i >= index; i--) {
			items[i + numElementsToAdd] = items[i];
		}

		int i = index;
		for (T element : c) {
			items[i++] = element;
		}

		size += numElementsToAdd;
		return true;
	}

	public boolean removeAll(Collection<?> c) {
		boolean modified = false;
		Iterator<?> it = c.iterator();
		while (it.hasNext()) {
			Object element = it.next();
			if (remove(element)) {
				modified = true;
			}
		}
		return modified;
	}

	public boolean retainAll(Collection<?> c) {
		boolean modified = false;
		Object[] newItems = new Object[size];
		int newIndex = 0;

		for (int i = 0; i < size; i++) {
			if (c.contains(items[i])) {
				newItems[newIndex++] = items[i];
			} else {
				modified = true;
			}
		}
		if (modified) {
			items = newItems;
			size = newIndex;
		}

		return modified;
	}

	public void clear() {
		items = new Object[10];
		size = 0;
	}

	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
			return (T[]) java.util.Arrays.copyOf(items, size, a.getClass());
		}

		System.arraycopy(items, 0, a, 0, size);

		a[size] = null;

		return a;
	}
}
