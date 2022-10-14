import java.util.*;

class Node {
    int data;
    Node next, bottom;

    Node(int val) {
        this.data = val;
        this.next = null;
    }
}


class LinkedList{
    Node head;

    void insert(int val) {
        Node myNode = new Node(val);
        if (head == null) {
            head = myNode;
        }
        else {
            Node temp = head;
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = myNode;
        }

    }

    void show() {
        Node temp1 = head;
        while(temp1.next != null) {
            System.out.println(temp1.data);
            temp1 = temp1.next;
        }
        System.out.println(temp1.data);
    }

    void insertAtFront(int new_val) {
        Node n = new Node(new_val);
        if(head == null) {
            head = n;
        }
        else {
            n.next = head;
            head = n;
        }
    }

    void insertAtLast(int new_val) {
        Node temp = head;
        Node n = new Node(new_val);

        if(head == null) {
            head = n;
            return;
        }
        // Traverse through last element
        while(temp.next != null) {
            temp = temp.next;
        }

        temp.next = n;
    }

    void insertAfterNode(int position, int new_val) {
        Node temp = head;

        if (head == null) {
            head = new Node(new_val);
        }

        else if(position == 0) {
            Node new_node = new  Node(new_val);
            new_node.next = head;
            head = new_node;
        }
        else {
            for(int i=1; i<position; i++) {
                if (temp.next != null) {
                    temp = temp.next;
                }
                else {
                    break;
                }
            }
            Node new_node = new Node(new_val);
            new_node.next = temp.next;
            temp.next = new_node;
        }
    }

    void deleteNode(int position) {
        if(head == null) {
            return;
        }

        Node temp = head;
        if(position == 0) {
            head = temp.next;
            return;
        }

        for(int i=1; temp!=null && i<position; i++) {
            temp = temp.next;
        }
        if (temp == null || temp.next == null)
            return;

        Node next = temp.next.next;
        temp.next = next;

    }

    void _printreverse(Node node) {
        if(node == null) {
            return;
        }
        else {
            _printreverse(node.next);
            System.out.println(node.data);
        }
    }

    void printInReverse() {
        Node temp = head;
        _printreverse(temp);
    }

    void reverseLinkedList() {
        if(head == null || head.next == null) {
            return ;
        }
        Node previous = null;
        Node current = head;
        Node future = null;

        while(current != null) {
            future = current.next;
            current.next = previous;
            previous = current;
            current = future;
        }
        head = previous;
    }

    void printNthFromLast(int positionFromTail) {
        // last node is marked as 0, then second last as 1 etc.....
        Node fast = head;
        Node slow = head;

        for(int i=0; i<positionFromTail; i++) {
            fast = fast.next;
        }

        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        System.out.println(slow.data);
    }

    void removeNthFromLast(int positionFromTail) {
        // last node is marked as 1, then second last as 2 etc.....
        if(head == null || head.next == null) {
            head = null;
        }
        Node fast = head;
        Node slow = head;
        Node prev_of_slow = null;

        for(int i=1; i<positionFromTail; i++) {
            fast = fast.next;
        }

        while(fast.next != null) {
            fast = fast.next;
            prev_of_slow = slow;
            slow = slow.next;
        }

        if(head == slow) {
            head = head.next;
        }
        else {
            prev_of_slow.next = slow.next;
        }
    }

    void removeNthFromLastMethod2(int positionFromTail) {
        if(head == null) {
            return;
        }
        Node current = head;
        Node previous = head;

        for(int i=0; i<positionFromTail; i++) {
            current = current.next;
        }

        while(current != null && current.next != null) {
            current = current.next;
            previous = previous.next;
        }

        if(current == null) {
            head = previous.next;
        }
        else {
            if (previous.next == null) {
                previous.next = null;
            }
            else {
                previous.next = previous.next.next;
            }
        }
    }

    public boolean hasCycle(Node head) {
        if(head == null) return false;

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                return true;
            }
        }
        return false;
    }

    int loopStartPoint() {
        //First whether loop exist and preserve the point where slow
        // and fast pointer meet
        if(head == null) {
            return 0;
        }
        Node fast = head;
        Node slow = head;

        while(slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break;
            }
        }

        // Now take a pointer from head again and loop traverse one by one
        // https://www.javatpoint.com/detect-loop-in-a-linked-list
        Node prev = head;
        while(prev != fast) {
            prev = prev.next;
            fast = fast.next;
        }
        return prev.data;
    }

    public static void removeLoop(Node head){
        // https://www.youtube.com/watch?v=aIR0s1tY2Vk
        // https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
        if(head == null || head.next == null) return;
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) {
                break;
            }
        }

        if(slow != fast) {
            return;
        }

        slow = head;
        if(slow != fast) {
            while(slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            fast.next = null;
        }
        else {
            // Loop is attached to fiest node
            while(fast.next != slow) {
                fast = fast.next;
            }
            fast.next = null;
        }

    }

    public void removeNthNodeFromLast(int n) {
        if(head == null) return;
        Node fast = head;
        Node slow = head;


        for(int i=1; i<=n; i++) {
            fast = fast.next;
        }

        while(fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        if (fast == null){
            head = head.next;
        }
        else {
            if(slow.next == fast) {
                slow.next = fast.next;
            }
            else {
                slow.next= slow.next.next;
            }
        }
    }

    Node swapPair(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node current = dummy;
        while(current.next != null && current.next.next != null) {
            Node first = current.next;
            Node second = current.next.next;
            first.next = second.next;
            second.next = first;
            current.next = second;
            current.next.next = first;
            current = current.next.next;
        }
        return dummy.next;
    }
    public Node reverseKGroupAddFirstMethod(Node head, int k) {
        // https://www.youtube.com/watch?v=EKgNMFCShO8
        // https://leetcode.com/problems/reverse-nodes-in-k-group/
        if(head == null || head.next == null || k<2) {
            return head;
        }

        int length = 0;
        Node current = head;
        while(current != null) {
            current = current.next;
            length++;
        }

        Node orgHead = null;
        Node orgTail = null;

        class AddFirstNode {
            Node tempHead = null;
            Node tempTail = null;

            public void addFirst(Node node) {
                if(tempHead == null) {
                    tempHead = node;
                    tempTail = node;
                }
                else {
                    node.next = tempHead;
                    tempHead = node;
                }
            }
        }

        AddFirstNode afn = new AddFirstNode();
        current = head;
        while(length >= k) {
            int tempK = k;
            while(tempK > 0) {
                Node future = current.next;
                current.next = null;
                afn.addFirst(current);
                current = future;
                tempK--;
            }

            if(orgHead == null) {
                orgHead = afn.tempHead;
                orgTail = afn.tempTail;
            }
            else {
                orgTail.next = afn.tempHead;
                orgTail = afn.tempTail;
            }
            afn.tempHead = null;
            afn.tempTail = null;
            length -= k;
        }

        orgTail.next = current;
        return orgHead;
    }

    public Node reverseListAddFirst(Node head) {
        // https://www.youtube.com/watch?v=TOztSNeXZuw
        // https://leetcode.com/problems/reverse-linked-list/
        if(head == null) return head;
        class AddFirst {
            Node head = null;
            Node tail = null;

            public void addFirst(Node node) {
                if(head == null) {
                    head = node;
                    tail = node;
                }
                else {
                    node.next = head;
                    head = node;
                }
            }
        }

        AddFirst afn = new AddFirst();

        Node current = head;
        while(current != null) {
            Node forward = current.next;
            current.next = null;
            afn.addFirst(current);
            current = forward;
        }

        return afn.head;
    }

    public Node deleteAllDuplicates(Node head) {
        // https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
        // https://www.youtube.com/watch?v=7PGsMXlMzGA
        if(head == null) return head;

        Node dummy = new Node(-1);
        Node itr = dummy;
        itr.next = head;

        Node current = head.next;

        while(current != null) {
            boolean flag = false;
            while(current != null && itr.next.data == current.data) {
                flag = true;
                current = current.next;
            }

            if(flag) {
                itr.next = current;
            }
            else {
                itr = itr.next;
            }

            if(current != null) {
                current = current.next;
            }
        }
        return dummy.next;
    }

    public Node getMid(Node head) {
        if(head == null || head.next == null) return head;

        Node fast = head;
        Node slow = head;

        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public Node reverse(Node head) {
        if(head == null || head.next == null) {
            return head;
        }

        Node prev = null;
        Node future = null;
        Node current = head;

        while(current != null) {
            future = current.next;
            current.next = prev;
            prev = current;
            current = future;
        }

        return prev;
    }

    public boolean isPalindrome(Node head) {
        // https://leetcode.com/problems/palindrome-linked-list/
        // https://www.youtube.com/watch?v=rP4zdxydE_0
        if(head == null || head.next == null) return true;

        Node midNode = getMid(head);
        Node nHead = midNode.next;
        midNode.next = null;

        Node head2 = reverse(nHead);

        Node c1 = head;
        Node c2 = head2;

        while(c2 != null) {
            if(c1.data != c2.data) {
                return false;
            }
            c1 = c1.next;
            c2 = c2.next;
        }

        return true;
    }

    Node Kreverse(Node head, int k) {
        Node current = head;
        Node prev = null;
        Node future = null;

        while(current != null && k>0) {
            future = current.next;
            current.next = prev;
            prev = current;
            current = future;
            k--;
        }
        return prev;
    }

    Node kGroupReverse(Node head, int k) {
        //https://www.youtube.com/watch?v=jTWEmztptCQ
        if(head == null || head.next == null || k<2) {
            return head;
        }

        Node root = new Node(0);
        root.next = head;
        Node current = head;
        Node prev = root;

        while(current != null) {
            Node tail = current;
            int listIndex = 0;
            while(current != null && listIndex < k) {
                current = current.next;
                listIndex++;
            }

            if(listIndex != k) {
                prev.next = tail;
            }
            else {
                prev.next = Kreverse(tail, k);
                prev = tail;
                tail.next = current;
            }
        }
        return root.next;
    }
    Node swapNode(Node head, int k) {
        //Swap Kth node from start and end
        if(head == null || head.next == null) {
            return head;
        }
        int i=1;
        Node slow = head;
        Node fast = head;
        while(fast != null && i<k) {
            fast = fast.next;
            i++;
        }
        if(i != k) {
            return head;
        }

        Node front  = fast;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        int temp = front.data;
        front.data = slow.data;
        slow.data = temp;
        return head;
    }
    Node removeElements(Node head, int val) {
        // https://leetcode.com/problems/remove-linked-list-elements/
        while(head != null && head.data == val) {
            head = head.next;
        }

        if(head == null) return head;

        Node current = head;
        Node prev = head;

        while(current != null) {
            if(current.data == val) {
                Node future = current.next;
                prev.next = current.next;
                current = future;
            }
            else {
                prev = current;
                current = current.next;
            }
        }
        return head;
    }

    public void moveZeroesMethod1(int[] nums) {
        int zp = 0;
        for(int num : nums) {
            if(num != 0) {
                nums[zp++] = num;
            }
        }
        for(; zp<nums.length; zp++) {
            nums[zp] = 0;
        }
    }

    public void moveZeroesMethod2(int[] nums) {
        int n = nums.length;
        if(n == 0 || n==1){
            return;
        }
        int last = -1;
        for(int i=0; i<n; i++) {
            if(nums[i] != 0) {
                last++;
                int temp = nums[i];
                nums[i] = nums[last];
                nums[last] = temp;
            }
        }
    }

    static int strStrTimeExceeded(String haystack, String needle) {
        // https://leetcode.com/problems/implement-strstr/
        if(needle.isEmpty()) return 0;

        int h_length = haystack.length();
        int n_length = needle.length();

        if(h_length < n_length) return -1;

        for(int i=0; i<=h_length-n_length; i++) {
            int j;
            for(j=0; j<n_length; j++) {
                if(needle.charAt(j) != haystack.charAt(j+i)) {
                    break;
                }
            }
            if(j == n_length) {
                return i;
            }
        }
        return -1;
    }

    static Node mergeTwoSortedLinkedList(Node head1, Node head2) {
        if(head1 == null || head2 == null) {
            return head1 != null ? head1 : head2;
        }

        Node dummy = new Node(1000);
        Node temp = dummy;
        Node temp1 = head1, temp2 = head2;

        while(temp1 != null && temp2 != null) {
            if(temp1.data <= temp2.data) {
                temp.next = temp1;
                temp1 = temp1.next;
            }
            else{
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }

        while(temp1 != null) {
            temp.next = temp1;
            temp1 = temp1.next;
            temp = temp.next;
        }

        while(temp2 != null) {
            temp.next = temp2;
            temp2 = temp2.next;
            temp = temp.next;
        }

        return dummy.next;
    }

    static Node getMidNode(Node head) {
        if(head == null || head.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head;

        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    static Node mergeSortLinkedList(Node head) {
        // https://www.youtube.com/watch?v=HUFibUCDt0U
        // https://leetcode.com/problems/sort-list
        if(head == null || head.next == null) {
            return head;
        }
        Node mid = getMidNode(head);
        Node midNext = mid.next;
        // This is to break the linkage and maintain two List henceforth
        mid.next = null;
        Node sortedList1 = mergeSortLinkedList(head);
        Node sortedList2 = mergeSortLinkedList(midNext);
        return mergeTwoSortedLinkedList(sortedList1, sortedList2);
    }

    public Node getIntersectionNode(Node headA, Node headB) {
        // https://leetcode.com/problems/intersection-of-two-linked-lists
        // https://www.youtube.com/watch?v=B4aqNarb0QQ
        if(headA == null || headB == null) return null;
        int countA=0, countB = 0;
        Node tempA = headA, tempB = headB;
        while(tempA.next != null) {
            tempA = tempA.next;
            countA++;
        }
        while(tempB.next != null) {
            tempB = tempB.next;
            countB++;
        }

        tempA = headA;
        tempB = headB;

        int diff = Math.abs(countA - countB);

        if(countA > countB) {
            while(diff != 0) {
                tempA = tempA.next;
                diff--;
            }
        }
        else {
            while(diff != 0) {
                tempB = tempB.next;
                diff--;
            }
        }
        while(tempA != tempB) {
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return tempB;
    }

    public Node detectCycle(Node head) {
        // Intersection Node In Two LinkedList Using Floyd Cycle Method
        // https://leetcode.com/problems/linked-list-cycle-ii
        // https://www.youtube.com/watch?v=lAi11qfSsTM
        if(head == null || head.next == null) return null;

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) {
                break;
            }
        }
        if(slow != fast) return null;
        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    Node merge(Node root1, Node root2) {
        if(root1 == null) return root2;
        if(root2 == null) return root1;

        Node current = new Node(-1);
        Node temp = current;

        while(root1 != null && root2 != null) {
            if(root1.data < root2.data) {
                current.bottom = root1;
                current = current.bottom;
                root1 = root1.bottom;
            }
            else {
                current.bottom = root2;
                current = current.bottom;
                root2 = root2.bottom;
            }
        }
        if(root1 == null) {
            current.bottom = root2;
        }
        else {
            current.bottom = root1;
        }
        return temp.bottom;
    }

    Node flatten(Node root) {
        // https://techiedelight.com/flatten-linked-list/
        // https://www.geeksforgeeks.org/flattening-a-linked-list/
        if(root == null) {
            return null;
        }
        Node nextNode = flatten(root.next);
        Node mergedNode = merge(root, nextNode);
        return mergedNode;
    }

    public static Node flattenListMultiLevel(Node root) {
        if(root == null) return null;
        Node nextNode = flattenListMultiLevel(root.next);
        if(root.bottom != null) {
            Node downNode = flattenListMultiLevel(root.bottom);
            root.next = downNode;
            Node current = downNode;
            // Getting tail of bottom nodes
            while(current.next != null) {
                current = current.next;
            }
            current.next = nextNode;
        }
        else {
            root.next = nextNode;
        }
        return root;
    }

    public Node reverseLL(Node head) {
        if (head == null || head.next == null) return head;

        Node prev = null;
        Node current = head;
        while (current != null) {
            Node future = current.next;
            current.next = prev;
            prev = current;
            current = future;
        }
        return prev;
    }
}



public class LinkedListRun{
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.insert(1);
        ll.insert(2);
//        ll.insert(3);
//        ll.insert(4);
//        ll.insert(5);
//        ll.show();
        ll.removeNthNodeFromLast(1);
        ll.show();

    }
}
