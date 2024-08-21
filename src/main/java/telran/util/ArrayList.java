package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private Object[] array;
    private int size;

    public ArrayList(int capacity) {
        array = new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean add(T obj) {
        if (size == array.length) {
            reallocate();
        }
        array[size++] = obj;
        return true;
    }

    private void reallocate() {
        array = Arrays.copyOf(array, array.length * 2);
    }

    @Override
    public boolean remove(T pattern) {
        Object[] copiedArray = Arrays.copyOf(array, array.length - 1);
        int i = 0;
        boolean fl = false;
        for (i = 0; i <= array.length; i++) {
            if (array[i].equals(pattern)) {
                System.arraycopy(array, 0, copiedArray, 0, i);
                System.arraycopy(array, i + 1, copiedArray, i, copiedArray.length - i);
                fl = true;
            }
        }
        return fl;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean res = true;
        if ((size() -1) > 0) {
            res = false;
        }
        return res;
    }

    @Override
    public boolean contains(T pattern) {
        return indexOf(pattern) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }
    private class ArrayListIterator implements Iterator<T> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) array[index++];
        }    
    }

    @Override
    public void add(int index, T obj) {
        System.arraycopy(array, 0, array, 0, index);
        array[index] = obj;
        System.arraycopy(array, index, array, index + 1, size - index);
    }

    @Override
    public T remove(int index) {
        T removed = get(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        return removed;
    }

    @Override
    public T get(int index) {
    return (T) array[index];
    }

    @Override
    public int indexOf(T pattern) {
        int index = 0;
        int i = 0;
        while (index < array.length && !array[i].equals(pattern)) {
            index++;
        }
        return index == array.length ? -1 : index;
    }

    @Override
    public int indexLastOf(T pattern) {
        int size = size();
        int lastIndex = size - 1;
        while (lastIndex >= 0 && !array[lastIndex].equals(pattern)) {
            lastIndex--;
        }
        return lastIndex == 0 ? -1 : lastIndex;
    }

}
