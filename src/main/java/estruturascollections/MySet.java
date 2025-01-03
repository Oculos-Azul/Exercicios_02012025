package estruturascollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MySet<T> {
    private Object[] elements;
    private int size;

    public MySet() {
        elements = new Object[10];
        size = 0;
    }

    public int getElementsCapacity() {
        return elements.length;
    }

    public boolean add(T element) {
        if (contains(element)) {
            return false;
        }

        if (size == elements.length) {
            grow();
        }

        elements[size++] = element;
        shuffleElements();
        return true;
    }

    private void shuffleElements() {
        if (size <= 1) {
            return;
        }

        for (int i = size - 1; i > 0; i--) {
            int randomIndex = (int) (Math.random() * (i + 1));
            Object temp = elements[i];
            elements[i] = elements[randomIndex];
            elements[randomIndex] = temp;
        }
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
            shuffleElements();
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

    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = elements[i];
        }
        return result;
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

    @SuppressWarnings("unchecked")
    public void sort() {
        quickSort((T[]) elements, 0, size - 1);
    }

    private void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (((Comparable<T>) array[j]).compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @SuppressWarnings("unchecked")
    public void sort(Comparator<T> comparator) {
        quickSort((T[]) elements, 0, size - 1, comparator);
    }

    private void quickSort(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, comparator);
            quickSort(array, low, pivotIndex - 1, comparator);
            quickSort(array, pivotIndex + 1, high, comparator);
        }
    }

    private int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }
}
