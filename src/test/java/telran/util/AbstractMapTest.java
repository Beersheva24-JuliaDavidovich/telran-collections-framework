package telran.util;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.Map.Entry;

public abstract class AbstractMapTest extends SetTest{
Integer[] keys = {1, -1};
Map<Integer, Integer> map;
void setUp() {
   collection = new AbstractMap<>();
   super.setUp();
}
abstract <T> void runTest(T [] expected, T [] actual);
//TODO tests
}