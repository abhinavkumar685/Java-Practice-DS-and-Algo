import java.util.*;


class DLLNode {
    int data;
    DLLNode next;
    DLLNode previous;
    DLLNode down;

    DLLNode(int val) {
        this.data = val;
        this.next = null;
        this.previous = null;
    }
}


class DoubleLinkedList {
    DLLNode head;

    void insert(int val) {
        DLLNode myNode = new DLLNode(val);
        if(head == null) {
            head = myNode;
        }
        else {
            DLLNode temp = head;
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = myNode;
            myNode.previous = temp;
        }
    }

    void reverseMethod2() {
        if(head.next == null) {
            return;
        }
        DLLNode current = head;
        DLLNode new_head = head;

        while(current != null) {
            DLLNode previous = current.previous;
            DLLNode future = current.next;
            current.previous = current.next;
            current.next = previous;
            new_head = current;
            current = future;
        }
        head = new_head;
    }

    void reverseBestMethod() {
        if(head == null) return;

        DLLNode previous = null;
        DLLNode current  = head;

        if(current.next == null && current.previous == null) return;

        while(current != null) {
            current.previous = current.next;
            current.next = previous;
            previous = current;
            current = current.previous;
        }
        head = previous;
    }

    public DLLNode flatten(DLLNode head) {
        // https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list
        if(head == null) return null;
        DLLNode nextNode = flatten(head.next);
        if(head.down != null) {
            DLLNode downNode = flatten(head.down);
            head.next = downNode;
            downNode.previous = head;

            // Getting tail of down nodes
            DLLNode current = downNode;
            while(current.next != null) {
                current = current.next;
            }
            current.next = nextNode;
            if(nextNode != null) {
                nextNode.previous = current;
            }
        }
        else {
            head.next = nextNode;
            if (nextNode != null) {
                nextNode.previous = head;
            }
        }
        head.down = null;
        return head;
    }

}

public class DoubleLinkedListRun {

}
