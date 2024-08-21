package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public abstract class ListTest extends CollectionTest {
    List<Integer> list;
    @Override
    void setUp() {
        super.setUp();
        list = (List<Integer>) collection;
    }
    @Test
    void addTest() {
        list.add(5, 10);
    } 
    @Test
    void removeTest() {
        Integer[] array = {3, -10, 20, 1, 10, 8, 100, 17};
        Integer[] expected = {3, 20, 1, 10, 8, 100, 17};
        list.remove(1);
       // assertArrayEquals(expected, array);
    }
    @Test
    void indexOfTest() {
        //assertEquals(3, list.indexOf(10));
    }
}
