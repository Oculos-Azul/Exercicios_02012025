package estruturascollections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MySet<T> {
    private Object[] elements;
    private int size;

    public MySet() {
        elements = new Object[10];
        size = 0;
    }

    public boolean add(T element) {
        if (contains(element)) {
            return false;
        }

        if (size == elements.length) {
            grow();
        }

        elements[size++] = element;
        return true;
    }

    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public boolean update(T oldElement, T newElement) {
        int index = indexOf(oldElement);
        if (index != -1) {
            elements[index] = newElement;
            return true;
        }
        return false;
    }

    public boolean remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            return false;
        }

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[--size] = null;
        return true;
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void grow() {
        int newLength = elements.length * 2;
        elements = Arrays.copyOf(elements, newLength);
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[currentIndex++];
            }
        };
    }

    public void sort() {
        Arrays.sort((T[]) elements, 0, size);
    }

    public void sort(Comparator<T> comparator) {
        Arrays.sort((T[]) elements, 0, size, comparator);
    }

    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    public void clear() {
        elements = new Object[10];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(elements[i]);
        }
    }
}
