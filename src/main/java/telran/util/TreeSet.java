package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class TreeSet<T> implements Set<T> {
    private static class Node<T> {
        T obj;
        Node<T> parent;
        Node<T> left;
        Node<T> right;

        Node(T obj) {
            this.obj = obj;
        }
    }

    private class TreeSetIterator implements Iterator<T> {
        Iterator<T> iterator;
        Node<T> current = getLeastFrom(root);
        Node<T> prevoiusNode;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        private Node<T> getLeastFrom(Node<T> root) {
            while (current != null) {
                current = current.left;
            }
            return current;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> nextCurrent = getNextCurrent();
            return (T) nextCurrent;
        }

        private Node<T> getNextCurrent() {
            Node<T> nextCurrent = null;
            if (current.right != null) {
                nextCurrent = getLeastFrom(current.right);
            } else {
                nextCurrent = getGreaterParent(current);
            }
            return nextCurrent;
        }

        private Node<T> getGreaterParent(Node<T> current) {
            Node<T> node = current;
            while (current != null && (comparator.compare(node.obj, current.obj) >= 0)) {
                current = current.parent;
            }
            return current;
        }

        @Override
        public void remove() {
            if (prevoiusNode == null) {
                throw new IllegalStateException();
            }
            removeNode(prevoiusNode);
            size--;
            prevoiusNode = null;
        }
    }

    private Node<T> root;
    private Comparator<T> comparator;
    int size;

    public TreeSet(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public TreeSet() {
        this((Comparator<T>) Comparator.naturalOrder());
    }

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            Node<T> node = new Node<>(obj);
            if (root == null) {
                addRoot(node);
            } else {
                addAfterParent(node);
            }
            size++;
        }
        return res;
    }

    private void addAfterParent(Node<T> node) {
        Node<T> parent = getParent(node.obj);
        if (comparator.compare(node.obj, parent.obj) > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        node.parent = parent;
    }

    private void addRoot(Node<T> node) {
        root = node;
    }

    @Override
    public boolean remove(T pattern) {
        boolean res = false;
        Node<T> node = getNode(pattern);
        if (node != null) {
            res = true;
            removeNode(node);
        }
        return res;
    }

    private void removeNode(Node<T> node) {
        if (node.left == null || node.right == null) {
            removeNonJunction(node);
        } else {
            removeJunctoin(node);
        }
    }

    private void removeJunctoin(Node<T> node) {
        if (node.left != null) {
            Node<T> nodeToParent = getGreatestFrom(node.left);
            node.obj = nodeToParent.obj;
            node.left = nodeToParent.left;
            node.right = nodeToParent.right;
            removeNonJunction(nodeToParent);
        }
    }

    private Node<T> getGreatestFrom(Node<T> left) {
        Node<T> greaterNode = left;
        while (left != null && (comparator.compare(greaterNode.obj, left.obj) >= 0)) {
            greaterNode = left.parent;
        }
        return greaterNode;
    }

    private void removeNonJunction(Node<T> node) {
        Node<T> parent = node.parent;
        Node<T> child = node.right != null ? node.right : node.left;
        if (parent != null) {
            if (parent.right == node) {
                parent.right = child;
            } else {
                parent.left = child;
            }
        }else {
            root = child;
        }
        if(child != null) {
            child.parent = parent;
        }
        node.obj = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T pattern) {
        return getNode(pattern) != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeSetIterator();
    }

    @Override
    public T get(Object pattern) {
        Node<T> res = getNode((T) pattern);
        return res.obj;
    }

    private Node<T> getParentOrNode(T pattern) {
        Node<T> current = root;
        Node<T> parent = null;
        int compRes = 0;
        while (current != null && (compRes = comparator.compare(pattern, current.obj)) != 0) {
            parent = current;
            current = compRes > 0 ? current.right : current.left;
        }
        return current == null ? parent : current;
    }

    private Node<T> getNode(T pattern) {
        Node<T> res = getParentOrNode(pattern);
        if (res != null) {
            int compRes = comparator.compare(pattern, res.obj);
            res = compRes == 0 ? res : null;
        }

        return res;

    }

    private Node<T> getParent(T pattern) {
        Node<T> res = getParentOrNode(pattern);
        int compRes = comparator.compare(pattern, res.obj);
        return compRes == 0 ? null : res;

    }
}