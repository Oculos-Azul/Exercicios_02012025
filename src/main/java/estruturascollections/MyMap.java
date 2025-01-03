package estruturascollections;

import java.util.Random;
import java.util.NoSuchElementException;

public class MyMap<K, V> {
    private Object[] keys;
    private Object[] values;
    private int size;

    public MyMap() {
        keys = new Object[10];
        values = new Object[10];
        size = 0;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("A chave não pode ser nula");
        }

        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                values[i] = value;
                shuffle();
                return;
            }
        }

        if (size == keys.length) {
            grow();
        }

        keys[size] = key;
        values[size] = value;
        size++;
        shuffle();
    }

    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return (V) values[i];
            }
        }
        throw new NoSuchElementException("Chave não encontrada");
    }

    public void remove(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                for (int j = i; j < size - 1; j++) {
                    keys[j] = keys[j + 1];
                    values[j] = values[j + 1];
                }
                keys[--size] = null;
                values[size] = null;
                shuffle();
                return;
            }
        }
        throw new NoSuchElementException("Chave não encontrada");
    }

    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    private void grow() {
        int newLength = keys.length * 2;
        Object[] newKeys = new Object[newLength];
        Object[] newValues = new Object[newLength];

        for (int i = 0; i < size; i++) {
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        }

        keys = newKeys;
        values = newValues;
    }

    private void shuffle() {
        Random random = new Random();
        for (int i = size - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Object tempKey = keys[i];
            Object tempValue = values[i];
            keys[i] = keys[j];
            values[i] = values[j];
            keys[j] = tempKey;
            values[j] = tempValue;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            keys[i] = null;
            values[i] = null;
        }
        size = 0;
    }
}
