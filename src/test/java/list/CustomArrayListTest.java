package list;

import list.impl.CustomArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomArrayListTest {

    @Test
    public void testAddAndSize() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertEquals(0, list.size());
        list.add(1);
        list.add(2);
        list.add(0, 5);
        assertEquals(3, list.size());
        assertNotEquals(0, list.size());
    }

    @Test
    public void testGet() {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("apple 1");
        list.add("apple 2");
        list.add("apple 3");
        assertEquals("apple 2", list.get(1));
    }

    @Test
    public void testRemove() {
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
    public void testClear() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
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
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        Object[] array = list.toArray();
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
    }

    @Test
    public void testSort() {
        CustomList<Integer> list = new CustomArrayList<>();
        list.add(20);
        list.add(19);
        list.add(17);
        list.add(15);
        list.add(14);
        list.add(12);
        list.add(21);
        list.add(1);
        list.sort(Integer::compareTo);
        assertEquals(1, list.get(0));
        assertEquals(14, list.get(2));
        assertEquals(21, list.get(7));
    }
}
