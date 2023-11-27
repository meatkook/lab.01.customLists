package list;

import list.impl.CustomArrayList;
import list.impl.CustomLinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomLinkedListTest {

    @Test
    public void testAddAndGet() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Apple");
        list.add("Banana");
        list.add(0,"Cucumber");
        assertEquals("Cucumber", list.get(0));
        assertEquals("Apple", list.get(1));
        assertEquals("Banana", list.get(2));
    }

    @Test
    public void testSize() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        assertEquals(0, list.size());
        list.add("A");
        list.add("B");
        assertEquals(2, list.size());
        list.remove(0);
        assertEquals(1, list.size());
    }

    @Test
    public void testRemove() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        assertTrue(list.remove("B"));
        assertEquals("D", list.remove(2));
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
        assertFalse(list.remove("D"));
    }

    @Test
    public void testClear() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testEquals() {
        CustomArrayList<String> list1 = new CustomArrayList<>();
        list1.add("apple");
        list1.add("banana");
        list1.add("cherry");

        CustomArrayList<String> list2 = new CustomArrayList<>();
        list2.add("apple");
        list2.add("banana");
        list2.add("cherry");

        CustomArrayList<String> list3 = new CustomArrayList<>();
        list3.add("apple");
        list3.add("cherry");

        assertEquals(list1, list2);
        assertNotEquals(list1, list3);
    }

    @Test
    public void testToArray() {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("One");
        list.add("Two");
        Object[] array = list.toArray();
        assertEquals("One", array[0]);
        assertEquals("Two", array[1]);
    }

    @Test
    public void testSortInteger() {
        CustomList<Integer> list = new CustomLinkedList<>();
        list.add(20);
        list.add(19);
        list.add(17);
        list.add(15);
        list.add(14);
        list.add(12);
        list.add(21);
        list.add(22);
        list.add(1);
        list.sort(Integer::compareTo);
        assertEquals(1, list.get(0));
        assertEquals(14, list.get(2));
        assertEquals(21, list.get(7));
    }

    @Test
    public void testSortString() {
        CustomList<String> stringList = new CustomLinkedList<>();
        stringList.add("Banana");
        stringList.add("Apple");
        stringList.add("kumquat");
        stringList.add("Kivy");
        stringList.add("Carrot");
        stringList.sort();
        assertEquals("Apple", stringList.get(0));
        assertEquals("Banana", stringList.get(1));
        assertEquals("Carrot", stringList.get(2));
        assertEquals("Kivy", stringList.get(3));
        assertEquals("kumquat", stringList.get(4));
    }

    @Test
    void testMixed() {
        CustomList<String> customLinkedList = new CustomLinkedList<>();
        customLinkedList.add("21");
        customLinkedList.add("32");
        customLinkedList.add("13");
        customLinkedList.add("1");
        customLinkedList.add("2");
        customLinkedList.add("3");
        customLinkedList.add("321");
        customLinkedList.add("432");
        customLinkedList.add("513");
        customLinkedList.add("61");
        customLinkedList.add("52");
        customLinkedList.add("345");

        assertEquals("32", customLinkedList.get(1));
        customLinkedList.remove(1);
        assertEquals("13", customLinkedList.get(1));
        // Before sort
        assertEquals("[21, 13, 1, 2, 3, 321, 432, 513, 61, 52, 345]", customLinkedList.toString());
        // After sort
        customLinkedList.sort(String::compareTo);
        assertEquals("[1, 13, 2, 21, 3, 321, 345, 432, 513, 52, 61]", customLinkedList.toString());
        // After clear
        customLinkedList.clear();
        assertEquals("[]", customLinkedList.toString());
    }
}