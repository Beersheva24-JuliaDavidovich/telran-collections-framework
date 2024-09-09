package telran.util;

import java.util.Iterator;
import telran.util.LinkedList.Node;

public class LinkedHashSet<T> implements Set<T> {
    private LinkedList<T> list = new LinkedList<>();
    HashMap<T, Node<T>> map = new HashMap<>();
    private int size;

    @Override
    public boolean add(T obj) {
        boolean res = true;
        if (!contains(obj)) {
            Node<T> node = new Node<>(obj);
            list.addNode(node, list.size());
            map.put(obj, node);

        }

        return res;
    }

    @Override
    public boolean remove(T pattern) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public int size() {
        return list.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T pattern) {
        return map.get(pattern) != null;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        //you have to write the LinkedHashSetIterator
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public T get(Object pattern) {
        Node<T> node = map.get(pattern);
        return node == null ? null : node.obj;
    }

}