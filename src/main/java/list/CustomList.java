package list;

import java.util.Comparator;

/**
 * This interface represents a custom list data structure.
 *
 * @param <E> the type of elements in the list
 */
public interface CustomList<E> extends Iterable<E> {

    /**
     * Adds the specified element to the end of the list.
     *
     * @param element the element to be added to the list
     */
    void add(E element);

    /**
     * Inserts the specified element at the specified position in the list.
     *
     * @param index   the index at which the specified element is to be inserted
     * @param element the element to be inserted into the list
     */
    void add(int index, E element);

    /**
     * Returns the element at the specified position in the list.
     *
     * @param index the index of the element to be returned
     * @return the element at the specified position in the list
     */
    E get(int index);

    /**
     * Removes the first occurrence of the specified element from the list, if it is present.
     *
     * @param o the element to be removed from the list, if present
     * @return true if the list contained the specified element, false otherwise
     */
    boolean remove(Object o);

    /**
     * Removes the element at the specified position in the list.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     */
    E remove(int index);

    /**
     * Removes all the elements from the list.
     */
    void clear();

    /**
     * Sorts the list according to the order induced by the specified comparator.
     *
     * @param comparator the comparator to determine the order of the list
     */
    void sort(Comparator<? super E> comparator);

    /**
     * Compares the specified object with the list for equality.
     *
     * @param o the object to be compared for equality with the list
     * @return true if the specified object is equal to the list, false otherwise
     */
    boolean equals(Object o);

    /**
     * Returns an array containing all the elements in the list in a proper sequence.
     *
     * @return an array containing all the elements in the list
     */
    Object[] toArray();
}