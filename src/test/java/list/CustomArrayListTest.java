package list;

import list.impl.CustomArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomArrayListTest {

    @Test
    void testAddAndSize() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertEquals(0, list.size());
        list.add(1);
        list.add(2);
        list.add(0, 5);
        assertEquals(3, list.size());
        assertNotEquals(0, list.size());
    }

    @Test
    void testGet() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("apple 1");
        list.add("apple 2");
        list.add("apple 3");
        assertEquals("apple 2", list.get(1));
    }

    @Test
    void testRemove() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("apple 1");
        list.add("apple 2");
        list.add("apple 3");
        assertEquals("apple 1", list.remove(0));
        assertTrue(list.remove("apple 2"));
        assertFalse(list.remove("apple 1"));
        assertEquals(1, list.size());
    }

    @Test
    void testClear() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void testEquals() {
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
    void testToArray() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        Object[] array = list.toArray();
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
    }

    @Test
    void testSortInteger() {
        CustomList<Integer> list = new CustomArrayList<>();
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
    void testSortStringOne() {
        CustomList<String> list = new CustomArrayList<>();
        list.add("Banana");
        list.add("Apple");
        list.add("kumquat");
        list.add("Kivy");
        list.add("Carrot");
        list.sort();
        assertEquals("Apple", list.get(0));
        assertEquals("Banana", list.get(1));
        assertEquals("Carrot", list.get(2));
        assertEquals("Kivy", list.get(3));
        assertEquals("kumquat", list.get(4));
    }

    @Test
    void testMixed() {
        CustomList<String> arrayList = new CustomArrayList<>();
        arrayList.add("21");
        arrayList.add("32");
        arrayList.add("13");
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("321");
        arrayList.add("432");
        arrayList.add("513");
        arrayList.add("61");
        arrayList.add("52");
        arrayList.add("345");

        assertEquals("32", arrayList.get(1));
        arrayList.remove(1);
        assertEquals("13", arrayList.get(1));
        // Before sort
        assertEquals("[21, 13, 1, 2, 3, 321, 432, 513, 61, 52, 345]", arrayList.toString());
        // After sort
        arrayList.sort(String::compareTo);
        assertEquals("[1, 13, 2, 21, 3, 321, 345, 432, 513, 52, 61]", arrayList.toString());
        // After clear
        arrayList.clear();
        assertEquals("[]", arrayList.toString());
    }
}