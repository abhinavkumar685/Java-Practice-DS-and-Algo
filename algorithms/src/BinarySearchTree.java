import java.util.*;

class BSTNode {
    int data;
    BSTNode left, right;

    BSTNode(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}

public class BinarySearchTree {
    static void inOrderTraversal(BSTNode root) {
        // Left, Node, Right
        if(root == null) return;
        inOrderTraversal(root.left);
        System.out.print(root.data + " ");
        inOrderTraversal(root.right);
    }

    static BSTNode insert(BSTNode root, int val) {
        if(root == null) {
            return new BSTNode(val);
        }
        if(val < root.data) {
            root.left = insert(root.left, val);
        }
        else if (val > root.data) {
            root.right = insert(root.right, val);
        }
        return root;
    }

    static BSTNode constructBSTFromInorder(int[] nums, int left, int right) {
        // Sorted List is Inorder Traversal of BST.
        // https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree
        // https://www.youtube.com/watch?v=UAsLKuEMhsQ
        // Input: constructBSTFromInorder(nums, 0, n-1);
        if(left > right) {
            return null;
        }
        int mid = (left + right) /2;
        BSTNode root = new BSTNode(nums[mid]);
        root.left = constructBSTFromInorder(nums, left, mid-1);
        root.right = constructBSTFromInorder(nums, mid+1, right);
        return root;
    }

    static int index = 0;
    static BSTNode createBSTFromPreorder(int[] preorder, int leftRange, int rightRange) {
        if(index >= preorder.length || preorder[index] < leftRange
                || preorder[index] > rightRange) {
            return null;
        }

        BSTNode root = new BSTNode(preorder[index++]);
        root.left =  createBSTFromPreorder(preorder, leftRange, root.data);
        root.right =  createBSTFromPreorder(preorder, root.data, rightRange);
        return root;
    }

    static BSTNode bstFromPreorder(int[] preorder) {
        // https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal
        // https://www.youtube.com/watch?v=Bexswo4pqZQ
        int n = preorder.length;
        if(n == 0) return null;
        int leftRange = 0;
        int rightRange = (int)1e8+1;
        return createBSTFromPreorder(preorder, leftRange, rightRange);
    }

    static BSTNode createBSTFromPostorder(int[] postorder, int leftRange, int rightRange) {
        if(index < 0 || postorder[index] < leftRange
                || postorder[index] > rightRange) {
            return null;
        }

        BSTNode root = new BSTNode(postorder[index--]);
        root.right =  createBSTFromPreorder(postorder, root.data, rightRange);
        root.left =  createBSTFromPreorder(postorder, leftRange, root.data);
        return root;
    }

    static BSTNode bstFromPostorder(int[] postorder) {
        // https://www.youtube.com/watch?v=KsGXE7_y2Nw
        int n = postorder.length;
        if(n == 0) return null;
        index = postorder.length - 1;
        int leftRange = 0;
        int rightRange = (int)1e8+1;
        return createBSTFromPostorder(postorder, leftRange, rightRange);
    }

    public int countNodes(BSTNode root) {
        // https://leetcode.com/problems/count-complete-tree-nodes/
        if(root == null) return 0;

        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);

        return 1 + leftCount + rightCount;
    }

    static int maxValue(BSTNode root) {
        if(root != null) {
            return maxValue(root.right);
        }
        else {
            return root.data;
        }
    }

    static int minValue(BSTNode root) {
        if(root != null) {
            return maxValue(root.left);
        }
        else {
            return root.data;
        }
    }

    static boolean find(BSTNode root, int val) {
        if(root == null) return false;

        if(root.data == val) {
            return true;
        }
        else if(val < root.data) {
            return find(root.left, val);
        }
        else {
            return find(root.right, val);
        }
    }

    static BSTNode addNode(BSTNode root, int val) {
        // https://www.youtube.com/watch?v=lraQt-zHOHk
        // https://leetcode.com/problems/insert-into-a-binary-search-tree/
        if(root == null) {
            return new BSTNode(val);
        }
        if(val < root.data) {
            root.left = addNode(root.left, val);
        }
        else if(val > root.data) {
            root.right = addNode(root.right, val);
        }
        return root;
    }

    public int max(BSTNode root) {
        if(root.right != null) {
            return max(root.right);
        }
        else {
            return root.data;
        }
    }

    public BSTNode deleteNode(BSTNode root, int key) {
        if(root == null) return null;

        if(key > root.data) {
            root.right = deleteNode(root.right, key);
        }
        else if(key < root.data) {
            root.left = deleteNode(root.left, key);
        }
        else {
            // leaf node
            if(root.left != null && root.right != null) {
                int lmax = max(root.left);
                root.left = deleteNode(root.left, lmax);
                root.data = lmax;
                return root;
            }
            else if(root.left != null) {
                return root.left;
            }
            else if(root.right != null) {
                return root.right;
            }
            else {
                return null;
            }
        }
        return root;
    }

    int sum = 0;
    public BSTNode largerBST(BSTNode root) {
        // https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
        // https://www.youtube.com/watch?v=MLff3CxNVTc
        if(root == null) return root;
        largerBST(root.right);
        int val = root.data;
        root.data = root.data + sum;
        sum += val;
        largerBST(root.left);
        return root;
    }

    static BSTNode removeMax(BSTNode root) {
        if(root == null) return null;

        if(root.left == null && root.right == null) {
            return null;
        }
        else {
            root.right = removeMax(root.right);
        }
        return root;
    }

    static int getMax(BSTNode root) {
        if (root.right == null) {
            return root.data;
        } else {
            return getMax(root.right);
        }
    }

    static BSTNode removeNode(BSTNode root, int val) {
        // https://www.youtube.com/watch?v=5_AZcOOc-kM
        // https://leetcode.com/problems/delete-node-in-a-bst
        if(root == null) return null;

        if(val < root.data) {
            root.left = removeNode(root.left, val);
        }
        else if(val > root.data) {
            root.right = removeNode(root.right, val);
        }
        else {
            if(root.left != null && root.right != null) {
                int lmax = getMax(root.left);
                root.data = lmax;
                root.left = removeNode(root.left, lmax);
                return root;
            }
            else if(root.left != null) {
                return root.left;
            }
            else if(root.right != null) {
                return root.right;
            }
            else {
                return null;
            }
        }
        return root;
    }

    static int nodeSum = 0;
    static void replaceLargerSum(BSTNode root) {
        // https://www.youtube.com/watch?v=MLff3CxNVTc
        if(root == null) return;
        replaceLargerSum(root.right);
        int nodeVal = root.data;
        root.data = nodeSum;
        nodeSum += nodeVal;
        replaceLargerSum(root.right);
    }

    public BSTNode lowestCommonAncestor(BSTNode root, BSTNode p, BSTNode q) {
        // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree
        // https://www.youtube.com/watch?v=5E3MeajU9XQ
        if(root == null) return null;

        if(p.data < root.data && q.data < root.data) {
            return lowestCommonAncestor(root.left, p, q);
        }
        else if(p.data > root.data && q.data > root.data) {
            return lowestCommonAncestor(root.right, p, q);
        }
        else {
            return root;
        }
    }

    public BSTNode lowestCommonAncestorIterative(BSTNode root, BSTNode p, BSTNode q) {
        while(root != null) {
            if(root.data > p.data && root.data > q.data) {
                root = root.left;
            }
            else if(root.data < p.data && root.data < q.data) {
                root = root.right;
            }
            else {
                return root;
            }
        }
        return null;
    }

    static void printRange(BSTNode root, int min, int max) {
        // https://www.youtube.com/watch?v=NEJUsqEFOI4
        if(root == null) return;

        if(root.data < min && root.data < max) {
            printRange(root.right, min, max);
        }
        else if(root.data > min && root.data > max) {
            printRange(root.left, min, max);
        }
        else {
            printRange(root.left, min, max);
            System.out.print(root.data + " ");
            printRange(root.right, min, max);
        }
    }
    static DLLNode midOfDLL(DLLNode head) {
        if(head == null || head.next == null) return head;
        DLLNode slow = head;
        DLLNode fast = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    static BSTNode DLLToBST(DLLNode head) {
        // https://www.youtube.com/watch?v=Knynb5QOSMg
        if(head == null) return null;
        DLLNode midNode = midOfDLL(head);
        DLLNode leftNode = midNode.previous;
        DLLNode rightNode = midNode.next;
        BSTNode root = new BSTNode(midNode.data);
        // Check if previous exist of mid-node
        if(leftNode != null) {
            leftNode.next = null;
        }
        midNode.previous = midNode.next = rightNode.previous = null;
        DLLNode leftHead = leftNode != null ? head : null;

        BSTNode left = DLLToBST(leftHead);
        BSTNode right = DLLToBST(rightNode);
        root.left = left;
        root.right = right;
        return root;
    }

    public static BSTNode getPre(BSTNode root) {
        root = root.left;
        while(root.right != null) {
            root = root.right;
        }
        return root;
    }

    public static BSTNode getSuc(BSTNode root) {
        root = root.right;
        while(root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static void find(BSTNode root, int key, BSTNode preSuc) {
        if(root == null) return;

        if(root.data == key) {
            preSuc.data = 1;
            if(root.left != null) {
                preSuc.left = getPre(root);
            }
            if(root.right != null) {
                preSuc.right = getSuc(root);
            }
            return;
        }

        if(key > root.data) {
            preSuc.left = root;
            find(root.right, key, preSuc);
        }
        else if(key < root.data) {
            preSuc.right = root;
            find(root.left, key, preSuc);
        }
    }

    public static ArrayList<Integer> findPreSuc(BSTNode root, int key) {
        // https://www.youtube.com/watch?v=lQIXz5NJYLs
        // https://www.codingninjas.com/codestudio/problems/predecessor-and-successor_920476
        ArrayList<Integer> result = new ArrayList<>();
        result.add(-1);
        result.add(-1);

        if(root == null) return result;
        /* preSuc to store intermediate result
         preSuc.data = 1 if key found else 0
         preSuc.left = inorder predecessor
         preSuc.right = inorder successor */
        BSTNode preSuc = new BSTNode(0);
        find(root, key, preSuc);

        if(preSuc.data == 1) {
            if(preSuc.left != null) {
                result.set(0, preSuc.left.data);
            }
            if(preSuc.right != null) {
                result.set(1, preSuc.right.data);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] keys = { 15, 10, 20, 8, 12, 16, 25 };

        BSTNode root = null;
        for (int key: keys) {
            root = insert(root, key);
        }

        inOrderTraversal(root);
    }
}
