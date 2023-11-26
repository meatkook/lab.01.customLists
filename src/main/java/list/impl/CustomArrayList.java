package list.impl;

import list.CustomList;

import java.util.*;
import java.util.function.Consumer;

/**
 This class implements the CustomList interface and provides a custom implementation of an ArrayList.
 @param <E> the type of elements in this list
 */
public class CustomArrayList<E> implements CustomList<E> {
    private static final int DEFAULT_CAPACITY = 7;
    private Object[] elements;
    private int size;

    /**
     * Constructs an empty list with an initial capacity of DEFAULT_CAPACITY.
     */
    public CustomArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Adds the specified element to the end of this list.
     *
     * @param element the element to be added to the list
     */
    @Override
    public void add(E element) {
        if (size == elements.length) {
            increaseCapacity();
        }
        elements[size] = element;
        size++;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param index   the index at which the specified element is to be inserted
     * @param element the element to be inserted into the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Requested index: " + index + ", but size: " + size);
        }
        if (size == elements.length) {
            increaseCapacity();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Requested index: " + index + ", but size: " + size);
        }
        return (E) elements[index];
    }

    /**
     * Removes the first occurrence of the specified element from this list if it is present.
     *
     * @param o the element to be removed from this list, if present
     * @return true if this list contained the specified element, false otherwise
     */
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                removeAtIndex(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Requested index: " + index + ", but size: " + size);
        }
        E oldValue = get(index);
        removeAtIndex(index);
        return oldValue;
    }

    /**
     * Removes all the elements from this list. The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }


    /**
     * Returns an array containing all the elements in this list in a proper sequence.
     *
     * @return an array containing all the elements in this list
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /**
     * Compares the specified object with this list for equality.
     *
     * @param o the object to be compared for equality with this list
     * @return true if the specified object is equal to this list, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomArrayList<?> that = (CustomArrayList<?>) o;
        if (size != that.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(elements[i], that.elements[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                return (E) elements[currentIndex++];
            }
        };
    }

    /**
     * Performs the given action for each element of the list until all elements have been processed or the action throws an exception.
     *
     * @param action the action to be performed on the elements
     */
    @Override
    public void forEach(Consumer<? super E> action) {
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            E element = (E) elements[i];
            action.accept(element);
        }
    }


    /**
     * Creates a late-binding and fail-fast Spliterator over the elements in this list.
     *
     * @return a Spliterator over the elements in this list
     */
    @Override
    public Spliterator<E> spliterator() {
        return new Spliterator<>() {
            private int currentIndex = 0;

            @SuppressWarnings("unchecked")
            @Override
            public boolean tryAdvance(Consumer<? super E> action) {
                if (currentIndex < size) {
                    action.accept((E) elements[currentIndex++]);
                    return true;
                }
                return false;
            }

            @Override
            public Spliterator<E> trySplit() {
                return null;
            }

            @Override
            public long estimateSize() {
                return size - currentIndex;
            }

            @Override
            public int characteristics() {
                return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
            }
        };
    }

    /**
     * Sorts this list according to the order induced by the specified comparator.
     * This implementation uses a variation of the merge sort algorithm to achieve stable sorting.
     *
     * @param comparator the comparator to determine the order of the list
     */
    @Override
    public void sort(Comparator<? super E> comparator) {
        if (size <= 1) {
            return;
        }

        Object[] temp = new Object[size];
        int blockSize = 1;

        while (blockSize < size) {
            int start = 0;
            while (start < size) {
                int mid = start + blockSize;
                int end = Math.min(start + 2 * blockSize, size);
                merge(comparator, temp, start, mid, end);
                start += 2 * blockSize;
            }

            System.arraycopy(temp, 0, elements, 0, size);
            blockSize *= 2;
        }
    }

    /**
     * Merges two sorted portions of the array into a single sorted portion.
     *
     * @param comparator the comparator to compare elements
     * @param temp      the temporary array used for merging
     * @param start     the start index of the first portion
     * @param mid       the end index of the first portion and start index of the second portion
     * @param end       the end index of the second portion
     */
    @SuppressWarnings("unchecked")
    private void merge(Comparator<? super E> comparator, Object[] temp, int start, int mid, int end) {
        int i = start;
        int j = mid;
        int index = start;

        while (i < mid && j < end) {
            if (comparator.compare((E) elements[i], (E) elements[j]) <= 0) {
                temp[index++] = elements[i++];
            } else {
                temp[index++] = elements[j++];
            }
        }

        while (i < mid) {
            temp[index++] = elements[i++];
        }

        while (j < end) {
            temp[index++] = elements[j++];
        }
    }

    /**
     * Removes the element at the specified index and shifts any subsequent elements to the left.
     *
     * @param index the index of the element to be removed
     */
    private void removeAtIndex(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    /**
     * Increases the capacity of the internal array when it is full.
     */
    private void increaseCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }
}