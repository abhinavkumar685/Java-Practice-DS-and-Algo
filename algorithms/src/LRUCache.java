import java.util.*;

class LRUCache {
    // https://leetcode.com/problems/lru-cache
    // https://www.youtube.com/watch?v=JxtmaAFfVBo
    class Node {
        int key, value;
        Node prev, next;
    }

    int capacity;
    HashMap<Integer, Node> cache;
    Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    private void addNode(Node node) {
        Node nbr = head.next;
        node.next = nbr;
        nbr.prev = node;

        head.next = node;
        node.prev = head;
    }

    private void removeNode(Node node) {
        Node prevNbr = node.prev;
        Node nextNbr = node.next;

        prevNbr.next = nextNbr;
        nextNbr.prev = prevNbr;

        node.next = null;
        node.prev = null;
    }

    private void moveToFront(Node node) {
        removeNode(node);
        addNode(node);
    }

    public int get(int key) {
        Node current = cache.get(key);
        if(current == null) return -1;

        moveToFront(current);
        return current.value;
    }

    public void put(int key, int value) {
        Node current = cache.get(key);
        if(current == null) {
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;

            if(cache.size() == capacity) {
                Node LRU_Node = tail.prev;
                cache.remove(LRU_Node.key);
                removeNode(LRU_Node);
            }
            cache.put(key, newNode);
            addNode(newNode);
        }
        else {
            current.value = value;
            moveToFront(current);
        }
    }
}