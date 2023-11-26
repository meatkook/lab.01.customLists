package list.impl;

import list.CustomList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements the CustomList interface and provides a custom implementation of a LinkedList.
 *
 * @param <E> the type of elements in this list
 */
public class CustomLinkedList<E> implements CustomList<E> {
    private Node<E> head;
    private int size;

    /**
     * This class represents a node in the linked list.
     *
     * @param <E> the type of elements in the node
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Adds the specified element to the end of this list.
     *
     * @param element the element to be added to this list
     */
    @Override
    public void add(E element) {
        add(size, element);
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
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            Node<E> newNode = new Node<>(element);
            newNode.next = head;
            head = newNode;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node<E> newNode = new Node<>(element);
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param o the element to be removed from this list, if present
     * @return true if this list contained the specified element, otherwise false
     */
    @Override
    public boolean remove(Object o) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(o)) {
            head = head.next;
            size--;
            return true;
        }
        Node<E> current = head;
        while (current.next != null) {
            if (current.next.data.equals(o)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E removedData;
        if (index == 0) {
            removedData = head.data;
            head = head.next;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedData = current.next.data;
            current.next = current.next.next;
        }
        size--;
        return removedData;
    }

    /**
     * Removes all the elements from this list.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Compares the specified object with this list for equality.
     *
     * @param o the object to be compared for equality with this list
     * @return true if the specified object is equal to this list, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomLinkedList<?> that = (CustomLinkedList<?>) o;

        if (size != that.size) return false;

        Node<E> current = head;
        Node<?> thatCurrent = that.head;
        while (current != null) {
            if (!current.data.equals(thatCurrent.data)) {
                return false;
            }
            current = current.next;
            thatCurrent = thatCurrent.next;
        }

        return true;
    }

    /**
     * Returns an array containing all the elements in this list in a proper sequence.
     *
     * @return an array containing all the elements in this list
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }
        return array;
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * Sorts this list according to the order induced by the specified comparator.
     * This implementation uses a variation of the merge sort algorithm to achieve stable sorting.
     *
     * @param comparator the comparator to determine the order of this list
     */
    @Override
    public void sort(Comparator<? super E> comparator) {
        int blockSize = 1;
        Node<E> dummy = new Node<>(null);
        dummy.next = head;

        while (blockSize < size) {
            Node<E> current = dummy.next;
            Node<E> tail = dummy;

            while (current != null) {
                Node<E> left = current;
                Node<E> right = split(left, blockSize);
                current = split(right, blockSize);
                tail = merge(left, right, tail, current, comparator);
            }
            blockSize *= 2;
        }

        head = dummy.next;
    }

    /**
     * Splits the linked list starting from the given node into two parts.
     *
     * @param start the starting node of the portion to be split
     * @param size  the size of the portion to be split
     * @return the head of the second part of the split
     */
    private Node<E> split(Node<E> start, int size) {
        if (start == null) {
            return null;
        }
        for (int i = 1; start.next != null && i < size; i++) {
            start = start.next;
        }
        Node<E> rest = start.next;
        start.next = null;
        return rest;
    }

    /**
     * Merges two portions of the linked list in sorted order according to the specified comparator.
     *
     * @param left       the head of the first portion to be merged
     * @param right      the head of the second portion to be merged
     * @param tail       the tail of the merged portion
     * @param next       the next node after the merged portion
     * @param comparator the comparator to determine the order of merging
     * @return the tail of the merged portion
     */
    private Node<E> merge(Node<E> left, Node<E> right, Node<E> tail, Node<E> next, Comparator<? super E> comparator) {
        while (left != null && right != null) {
            if (comparator.compare(left.data, right.data) <= 0) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        tail.next = (left != null) ? left : right;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = next;
        return tail;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

}
