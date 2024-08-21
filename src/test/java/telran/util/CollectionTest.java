package telran.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public abstract class CollectionTest {
    protected Collection<Integer> collection;
    Integer[] array = {3, -10, 20, 1, 10, 8, 100, 17};
    void setUp() {
        Arrays.stream(array).forEach(n -> collection.add(n));//(collection::add)
    }
    @Test
    void addTest() {
        assertTrue(collection.add(200));
        assertTrue(collection.add(17));
        assertEquals(array.length + 2, collection.size());
    }
    @Test
    void sizeTest() {
        assertEquals(array.length, collection.size());
    }
    @Test
    void removeTest() {
        assertTrue(collection.remove(20));
        assertFalse(collection.remove(2));
    }
    @Test
    void isEmptyTest() {
        assertFalse(collection.isEmpty());
    }
    @Test
    void containsTest() {
       // assertTrue(collection.contains(10));
        assertFalse(collection.contains(101));
    }
}
