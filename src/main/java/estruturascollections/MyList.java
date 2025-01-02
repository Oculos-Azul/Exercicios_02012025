package estruturascollections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyList<T> implements List<T> {
    private Object[] items;
    private int size;

    public MyList() {
        items = new Object[10];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(items[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return (T) items[currentIndex++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = items[i];
        }
        return newArray;
    }

    @Override
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

    private void grow(int numElementsToAdd) {
        int newLength = items.length + numElementsToAdd;
        Object[] newItems = new Object[newLength];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }

    @Override
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

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (T) items[index];
    }

    @Override
    public T set(int index, Object element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object oldValue = items[index];
        items[index] = element;
        return (T) oldValue;
    }

    @Override
    public void add(int index, Object element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
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

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object removedItem = items[index];
        removeAt(index);
        return (T) removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(items[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(items[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
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
                return (T) items[currentIndex++];
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public T previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                return (T) items[--currentIndex];
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                if (currentIndex <= 0 || currentIndex > size) {
                    throw new IllegalStateException();
                }
                MyList.this.remove(--currentIndex);
            }

            @Override
            public void set(T e) {
                if (currentIndex <= 0 || currentIndex > size) {
                    throw new IllegalStateException();
                }
                MyList.this.set(currentIndex - 1, e);
            }

            @Override
            public void add(T e) {
                MyList.this.add(currentIndex++, e);
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return new ListIterator<T>() {
            private int currentIndex = index;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) items[currentIndex++];
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public T previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                return (T) items[--currentIndex];
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                if (currentIndex <= 0 || currentIndex > size) {
                    throw new IllegalStateException();
                }
                MyList.this.remove(--currentIndex);
            }

            @Override
            public void set(T e) {
                if (currentIndex <= 0 || currentIndex > size) {
                    throw new IllegalStateException();
                }
                MyList.this.set(currentIndex - 1, e);
            }

            @Override
            public void add(T e) {
                MyList.this.add(currentIndex++, e);
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        MyList<T> subList = new MyList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add((T) items[i]); // Cast para T
        }
        return subList;
    }

    // Métodos não implementados ainda
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (Object element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        int numElementsToAdd = c.size();
        if (size + numElementsToAdd > items.length) {
            grow(numElementsToAdd);
        }

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

    @Override
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

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            Object element = iterator.next();
            if (!c.contains(element)) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        items = new Object[10];
        size = 0;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) java.util.Arrays.copyOf(items, size, a.getClass());
        }

        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }
}
