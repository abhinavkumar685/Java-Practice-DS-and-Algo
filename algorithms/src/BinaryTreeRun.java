import java.util.*;
import java.util.LinkedList;

class BTNode {
    int data;
    BTNode left, right;

    BTNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BTNode1 {
    int data;
    BTNode1 left, right, next;

    BTNode1(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.next = null;
    }
}

class BTNode2 {
    String data;
    BTNode2 left, right;

    BTNode2(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BTNodePair {
    // For vertical traversal, top and bottom view
    BTNode node;
    int width;

    BTNodePair(BTNode node, int width) {
        this.node = node;
        this.width = width;
    }
}

class BTNodePairIterative {
    BTNode node;
    int status;

    BTNodePairIterative(BTNode node, int status) {
        this.node = node;
        this.status =  status;
    }
}

class DiameterHeightPair {
    int diameter;
    int height;
}

class TiltPair {
    int sum;
    int tilt;
}

class BSTPair {
    int max;
    int min;
    boolean isBST;
}

class BalancedTreePair {
    boolean isBalanced;
    int height;
}

class LargestBSTPair {
    int max;
    int min;
    boolean isBST;
    BTNode root;
    int size;
}

class PathSumLeafPair {
    int leafToLeafMaxSum = -(int)1e9 - 1;
    int nodeToLeafMaxSum = -(int)1e9 - 1;
}

class PathSumPair {
    int NTN_MaxSum = -(int)1e9;
    int RTN_MaxSum = 0;
}

class SumTreePair {
    int sum;
    boolean isSumTree;
}

class CousinPair {
    BTNode parent = null;
    int level = Integer.MAX_VALUE;
}

class BinaryTree {
    Scanner scan = new Scanner(System.in);
    int max_level = -1;

    BTNode createTree() {
        BTNode root = null;
        System.out.println("Enter data: ");
        int data = scan.nextInt();

        if(data == -1) {
            return null;
        }
        root = new BTNode(data);

        System.out.println("Enter Left of data " + data);
        root.left = createTree();

        System.out.println("Enter Right of data " + data);
        root.right = createTree();

        return root;
    }

    public static BTNode convertLinkedListToBinaryTree(Node head) {
        // https://www.techiedelight.com/construct-complete-binary-tree-from-linked-list/
        if (head == null) {
            return null;
        }
        BTNode root = new BTNode(head.data);
        head = head.next;
        Queue<BTNode> q = new ArrayDeque<>();
        q.add(root);

        while (head != null)
        {
            BTNode front = q.poll();
            front.left = new BTNode(head.data);
            q.add(front.left);
            head = head.next;
            if (head != null)
            {
                front.right = new BTNode(head.data);
                q.add(front.right);
                head = head.next;
            }
        }
        return root;
    }


    static BTNode createTree(Integer[] nodeList) {
        /* Integer[] nodeList = {50, 25, 12, null, null, 37, 30, null,
        null, null, 75, 62, null, 70, null, null, 87, null, null};
        */
        // https://www.youtube.com/watch?v=XV1ADVV6FbQ
        if(nodeList.length == 0) {
            return null;
        }
        Stack<BTNodePairIterative> stack = new Stack<>();
        BTNode root = new BTNode(nodeList[0]);

        BTNodePairIterative myPair = new BTNodePairIterative(root, 1);
        stack.push(myPair);

        int index = 0;
        while(stack.size() > 0) {
            BTNodePairIterative top = stack.peek();
            if(top.status == 1) {
                index++;
                if(nodeList[index] != null) {
                    top.node.left = new BTNode(nodeList[index]);
                    BTNodePairIterative leftPair = new BTNodePairIterative(top.node.left, 1);
                    stack.push(leftPair);
                }
                else {
                    top.node.left = null;
                }
                top.status += 1;
            }
            else if(top.status == 2) {
                index++;
                if(nodeList[index] != null) {
                    top.node.right = new BTNode(nodeList[index]);
                    BTNodePairIterative rightPair = new BTNodePairIterative(top.node.right, 1);
                    stack.push(rightPair);
                }
                else {
                    top.node.right = null;
                }

                top.status += 1;
            }
            else {
                stack.pop();
            }
        }
        return root;
    }

    public BTNode createTreeFromPreorderAndInorder(int[] preorder, int[] inorder,
                                                   int preStart, int preEnd, int inStart, int inEnd) {
        // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
        // https://www.youtube.com/watch?v=oAbSNJ35qAs
        // Input: createTreeFromPreorderAndInorder(preorder, inorder, 0, n-1, 0, n-1);
        if(inStart > inEnd) {
            return null;
        }
        int index = inStart;
        while(inorder[index] != preorder[preStart]) {
            index++;
        }
        int count = index - inStart;
        BTNode root = new BTNode(preorder[preStart]);
        root.left = createTreeFromPreorderAndInorder(preorder, inorder,
                preStart+1, preStart+count, inStart, index-1);
        root.right = createTreeFromPreorderAndInorder(preorder, inorder,
                preStart+count+1, preEnd, index+1, inEnd);
        return root;
    }

    public BTNode createTreeFromPostorderAndInorder(int[] postorder, int[] inorder, int postStart,
                                                    int postEnd, int inStart, int inEnd) {
        // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
        // https://www.youtube.com/watch?v=Lc3RBGtyn7M
        // Input = createTreeFromPostorderAndInorder(postorder, inorder, 0, n-1, 0, n-1);
        if(inStart > inEnd) {
            return null;
        }
        int index = inStart;
        while(inorder[index] != postorder[postEnd]) {
            index++;
        }
        int count = index - inStart;

        BTNode root = new BTNode(postorder[postEnd]);
        root.left = createTreeFromPostorderAndInorder(postorder, inorder, postStart,
                postStart+count-1, inStart, index-1);
        root.right = createTreeFromPostorderAndInorder(postorder, inorder, postStart+count,
                postEnd-1, index+1, inEnd);
        return root;
    }

    static void iterativePrePostInOrder(BTNode root) {
        // https://www.youtube.com/watch?v=12aMTS0L6WI
        if(root == null) return;
        List<Integer> preOrder = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();

        Stack<BTNodePairIterative> stack = new Stack<>();
        stack.push(new BTNodePairIterative(root, 1));

        while(!stack.isEmpty()) {
            BTNodePairIterative current = stack.peek();
            if(current.status == 1) {
                preOrder.add(current.node.data);
                current.status++;
                if(current.node.left != null) {
                    stack.push(new BTNodePairIterative(current.node.left, 1));
                }
            }
            else if(current.status == 2) {
                inOrder.add(current.node.data);
                current.status++;
                if(current.node.right != null) {
                    stack.push(new BTNodePairIterative(current.node.right, 1));
                }
            }
            else {
                postOrder.add(current.node.data);
                stack.pop();
            }
        }
        System.out.println("Preorder: " + preOrder);
        System.out.println("Inorder: " + inOrder);
        System.out.println("Postorder: " + postOrder);
    }

    static void inOrderTraversal(BTNode root) {
        // https://www.youtube.com/watch?v=BuVNOAh33No
        // Left, Node, Right
        if(root == null) return;

        inOrderTraversal(root.left);
        System.out.print(root.data + " ");
        inOrderTraversal(root.right);
    }

    static void inOrderWithoutRecursion(BTNode root) {
        if(root == null) {
            return;
        }
        BTNode current = root;
        Stack<BTNode> stack = new Stack<>();
        while(!stack.isEmpty() || current != null) {
            if(current != null) {
                stack.push(current);
                current = current.left;
            }
            else {
                current = stack.pop();
                System.out.print(current.data + " ");
                current = current.right;
            }
        }
    }

    static void preOrderTraversal(BTNode root) {
        // https://www.youtube.com/watch?v=BuVNOAh33No
        // Node, Left, Right
        if(root == null) return;
        System.out.print(root.data + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);

    }

    static void preOrderTraversalWithoutRecursion(BTNode root) {
        if(root == null) {
            return;
        }
        BTNode current = root;
        Stack<BTNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            current = stack.pop();
            System.out.print(current.data + " ");

            if(current.right !=  null) {
                stack.push(current.right);
            }
            if(current.left !=  null) {
                stack.push(current.left);
            }
        }
    }

    static void postOrderTraversal(BTNode root) {
        // https://www.youtube.com/watch?v=BuVNOAh33No
        // Left, Right, Node
        if(root == null) return;

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data + " ");

    }

    static void postOrderTraversalWithoutRecursion(BTNode root) {
        if(root == null) {
            return;
        }
        Stack<BTNode> stack = new Stack<>();
        stack.push(root);
        Stack<Integer> out = new Stack<>();
        while (!stack.empty())
        {
            BTNode curr = stack.pop();
            out.push(curr.data);

            if (curr.left != null) {
                stack.push(curr.left);
            }

            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        while (!out.empty()) {
            System.out.print(out.pop() + " ");
        }
    }

    static BTNode1 prevInorder = null;
    public static void setInorderSuccessor(BTNode1 root) {
        // https://techiedelight.com/practice/?problem=SetInorderSuccessor
        // https://www.youtube.com/watch?v=os0of0znlRk
        if(root == null) return;

        setInorderSuccessor(root.left);
        if(prevInorder != null) {
            prevInorder.next = root;
        }
        prevInorder = root;
        setInorderSuccessor(root.right);
    }

    static boolean nodeToRootPathValues(BTNode root, int value, List<Integer> result) {
        // https://www.youtube.com/watch?v=1Kyc-zQS7eQ
        if(root == null) {
            return false;
        }
        if(root.data == value) {
            result.add(root.data);
            return true;
        }
        if(nodeToRootPathValues(root.left, value, result)) {
            result.add(root.data);
            return true;
        }
        if(nodeToRootPathValues(root.right, value, result)) {
            result.add(root.data);
            return true;
        }
        return false;
    }

    static void printKLevelsDown(BTNode root, int level) {
        // https://www.youtube.com/watch?v=KvcfuGcdDMg
        if(root == null || level < 0) {
            return;
        }
        if(level == 0) {
            System.out.print(root.data + " ");
            return;
        }
        printKLevelsDown(root.left, level-1);
        printKLevelsDown(root.right, level-1);
    }

    static boolean nodeToRootPath(BTNode root, int value, List<BTNode> nodePath) {
        if(root == null) {
            return false;
        }
        if(root.data == value) {
            nodePath.add(root);
            return true;
        }
        if(nodeToRootPath(root.left, value, nodePath)) {
            nodePath.add(root);
            return true;
        }
        if(nodeToRootPath(root.right, value, nodePath)) {
            nodePath.add(root);
            return true;
        }
        return false;
    }

    static void printKLevelsDown(BTNode root, int level, BTNode blockNode) {
        if(root == null || level < 0 || root == blockNode) {
            return;
        }
        if(level == 0) {
            System.out.print(root.data + " ");
            return;
        }
        printKLevelsDown(root.left, level-1, blockNode);
        printKLevelsDown(root.right, level-1, blockNode);
    }

    static void printNodesKLevelFar(BTNode root, int value, int k) {
        // https://www.youtube.com/watch?v=B89In5BctFA
        // https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree
        if(root == null) return;
        List<BTNode> nodePath = new ArrayList<>();
        nodeToRootPath(root, value, nodePath);
        for(int i=0; i<nodePath.size(); i++) {
            printKLevelsDown(nodePath.get(i), k-i, i==0?null:nodePath.get(i-1));
        }
    }

    static void binaryTreePathsDFS(BTNode root,  String current, List<String> result) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            current += root.data;
            result.add(current);
            return;
        }
        binaryTreePathsDFS(root.left, current + root.data + "->", result);
        binaryTreePathsDFS(root.right,  current + root.data + "->", result);
    }

    static List<String> binaryTreePaths(BTNode root) {
        // https://leetcode.com/problems/binary-tree-paths
        List<String> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        binaryTreePathsDFS(root, "", result);
        return result;
    }

    static void rootToLeafPaths(BTNode root, List<Integer> current, List<List<Integer>> result) {
        if(root == null) {
            return;
        }
        if(root.left == null && root.right == null) {
            current.add(root.data);
            result.add(new ArrayList<Integer>(current));
            current.remove(current.size()-1);
            return;
        }
        current.add(root.data);
        rootToLeafPaths(root.left, current, result);
        rootToLeafPaths(root.right, current, result);
        current.remove(current.size()-1);
    }

    static PathSumLeafPair maxPathSumBetweenLeafNodes(BTNode root) {
        PathSumLeafPair myPair = new PathSumLeafPair();

        if(root == null) {
            return myPair;
        }

        if(root.left == null && root.right == null) {
            myPair.nodeToLeafMaxSum = root.data;
            return myPair;
        }

        PathSumLeafPair leftPair = maxPathSumBetweenLeafNodes(root.left);
        PathSumLeafPair rightPair = maxPathSumBetweenLeafNodes(root.right);

        myPair.leafToLeafMaxSum = Math.max(leftPair.leafToLeafMaxSum, rightPair.leafToLeafMaxSum);

        if(root.left != null && root.right != null) {
            myPair.leafToLeafMaxSum = Math.max(myPair.leafToLeafMaxSum, leftPair.nodeToLeafMaxSum + root.data + rightPair.nodeToLeafMaxSum);
        }

        myPair.nodeToLeafMaxSum = Math.max(leftPair.nodeToLeafMaxSum, rightPair.nodeToLeafMaxSum) + root.data;
        return myPair;
    }

    static int leafToLeafMaxSum(BTNode root) {
        // https://www.youtube.com/watch?v=kSSAdqB7qsw
        return maxPathSumBetweenLeafNodes(root).leafToLeafMaxSum;
    }

    static int getMax(int... arr) {
        int max = arr[0];
        for(int i: arr) {
            max = Math.max(max, i);
        }
        return max;
    }

    static PathSumPair maxPathSum(BTNode root) {
        // https://leetcode.com/problems/binary-tree-maximum-path-sum
        // https://www.youtube.com/watch?v=ElKcXGkYldA
        PathSumPair myPair = new PathSumPair();
        if(root == null) return myPair;

        PathSumPair leftPair = maxPathSum(root.left);
        PathSumPair rightPair = maxPathSum(root.right);

        int RTN_MaxSum = Math.max(leftPair.RTN_MaxSum, rightPair.RTN_MaxSum) + root.data;
        myPair.NTN_MaxSum = getMax(leftPair.NTN_MaxSum, rightPair.NTN_MaxSum,
                leftPair.RTN_MaxSum + root.data + rightPair.RTN_MaxSum, root.data, RTN_MaxSum);
        myPair.RTN_MaxSum = Math.max(RTN_MaxSum, root.data);
        return myPair;
    }

    static BTNode transformToLeftCloneTree(BTNode root) {
        // https://www.youtube.com/watch?v=TO7trQloRXc
        if(root == null) {
            return null;
        }
        BTNode newLeftNode = transformToLeftCloneTree(root.left);
        BTNode newRightNode = transformToLeftCloneTree(root.right);

        BTNode newNode = new BTNode(root.data);
        newNode.left = newLeftNode;
        root.left = newNode;
        root.right = newRightNode;
        return root;
    }

    static BTNode transformFromLeftCloneTreeToNormalTree(BTNode root) {
        // https://www.youtube.com/watch?v=AvXVChZoZkU
        if(root == null) {
            return null;
        }
        BTNode newLeftNode = transformFromLeftCloneTreeToNormalTree(root.left.left);
        BTNode newRightNode = transformFromLeftCloneTreeToNormalTree(root.right);

        root.left = newLeftNode;
        root.right = newRightNode;
        return root;
    }

    static void printSingleChildNodes(BTNode root) {
        // https://www.youtube.com/watch?v=VMJCFRWvb9Q
        if(root == null) {
            return;
        }
        if(root.left == null && root.right != null) {
            System.out.println(root.data);
        }
        if(root.right == null && root.left != null) {
            System.out.println(root.data);
        }
        printSingleChildNodes(root.left);
        printSingleChildNodes(root.right);
    }

    static BTNode removeLeafNodes(BTNode root) {
        if(root == null) {
            return null;
        }
        if(root.left == null && root.right == null) {
            return null;
        }
        BTNode newLeft = removeLeafNodes(root.left);
        BTNode newRight = removeLeafNodes(root.right);
        root.left = newLeft;
        root.right = newRight;
        return root;
    }

    static void removeLeavesMethod2(BTNode root, BTNode parent) {
        if(root == null) {
            return;
        }
        if(parent != null && root.left == null && root.right == null) {
            if(parent.left == root){
                parent.left = null;
            }
            else{
                parent.right = null;
            }
        }
        removeLeavesMethod2(root.left, root);
        removeLeavesMethod2(root.right, root);
    }

    static int nodesCount(BTNode root) {
        if(root  == null) {
            return 0;
        }

        int leftCount = nodesCount(root.left);
        int rightCount = nodesCount(root.right);

        return 1 + leftCount + rightCount;
    }

    static int nodeSum(BTNode root) {
        if(root == null) {
            return 0;
        }
        int leftSum = nodeSum(root.left);
        int rightSum = nodeSum(root.right);
        return root.data + leftSum + rightSum;
    }

    static int maxNode(BTNode root) {
        if(root == null) {
            return Integer.MIN_VALUE;
        }

        int leftMax = maxNode(root.left);
        int rightMax = maxNode(root.right);
        return Math.max(root.data, Math.max(leftMax, rightMax));
    }

    static TiltPair getTilt(BTNode root) {
        if(root == null) {
            TiltPair base = new TiltPair();
            base.sum = 0;
            base.tilt = 0;
            return base;
        }

        TiltPair leftTilt = getTilt(root.left);
        TiltPair rightTilt = getTilt(root.right);

        TiltPair myPair = new TiltPair();
        myPair.sum = root.data + leftTilt.sum + rightTilt.sum;

        myPair.tilt = Math.abs(leftTilt.sum - rightTilt.sum) + leftTilt.tilt + rightTilt.tilt;;
        return myPair;
    }

    static int findTilt(BTNode root) {
        // https://www.youtube.com/watch?v=gK95sG7Dm-w
        // https://leetcode.com/problems/binary-tree-tilt
        if(root == null) return 0;
        TiltPair result = getTilt(root);
        return result.tilt;
    }

    static void widthOfTreeShadowRecursion(BTNode node, int currentWidth, int[] minMax) {
        if(node == null) {
            return;
        }

        minMax[0] = Math.min(currentWidth, minMax[0]);
        minMax[1] = Math.max(currentWidth, minMax[1]);

        widthOfTreeShadowRecursion(node.left, currentWidth - 1, minMax);
        widthOfTreeShadowRecursion(node.right, currentWidth + 1, minMax);
    }

    static int widthOfTreeShadow(BTNode root) {
        if(root == null) return 0;
        int[] minMax = {0, 0};
        widthOfTreeShadowRecursion(root, 0, minMax);
        return minMax[1] - minMax[0] + 1;
    }

    static void verticalTraversal(BTNode root) {
        //https://www.youtube.com/watch?v=dGRkgGQgruQ
        // Useful for Top and Bottom View
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(root == null) return;

        int[] minMax = {0, 0};
        widthOfTreeShadowRecursion(root, 0, minMax);

        int treeWidth = minMax[1] - minMax[0] + 1;
        for(int i=0; i<treeWidth; i++) {
            result.add(new ArrayList<Integer>());
        }
        // Here we need to use ArrayList instead of HashMap to store data.
        // Array index should start with 0 instead of -ve,
        // hence we shift the root node width from 0 to positive value while pushing in Queue

        //Note: Here absolute value is taken since there may be no left child only right child
        int rootNodeWidth = Math.abs(minMax[0]);

        Queue<BTNodePair> queue = new LinkedList<>();
        queue.add(new BTNodePair(root, rootNodeWidth));

        while(!queue.isEmpty()) {
            BTNodePair current = queue.remove();
            result.get(current.width).add(current.node.data);

            if(current.node.left != null) {
                queue.add(new BTNodePair(current.node.left, current.width - 1));
            }

            if(current.node.right != null) {
                queue.add(new BTNodePair(current.node.right, current.width + 1));
            }
        }
        System.out.println(result);
    }

    static void verticalTraversalUsingHashMap(BTNode root) {
        // https://www.youtube.com/watch?v=LscPXvD1N1A
        if(root == null) return;
        int maxWidth = 0, minWidth = 0;
        Queue<BTNodePair> queue = new LinkedList<>();
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        queue.add(new BTNodePair(root, 0));
        while(!queue.isEmpty()) {
            BTNodePair temp = queue.remove();
            int currentWidth = temp.width;
            BTNode currentNode = temp.node;
            maxWidth = Math.max(maxWidth, currentWidth);
            minWidth = Math.min(minWidth, currentWidth);

            map.putIfAbsent(currentWidth, new ArrayList<Integer>());
            map.get(currentWidth).add(currentNode.data);

            if(currentNode.left != null) {
                queue.add(new BTNodePair(currentNode.left, currentWidth-1));
            }

            if(currentNode.right != null) {
                queue.add(new BTNodePair(currentNode.right, currentWidth+1));
            }
        }

        for(int i=minWidth; i<=maxWidth; i++) {
            result.add(map.get(i));
        }
        System.out.println(result);
    }

    static void bottomView(BTNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return;

        int[] minMax = {0, 0};
        widthOfTreeShadowRecursion(root, 0, minMax);
        int treeWidth = minMax[1] - minMax[0] + 1;
        for(int i=0; i<treeWidth; i++) {
            result.add(0);
        }
        int rootNodeWidth = Math.abs(minMax[0]);

        Queue<BTNodePair> queue = new LinkedList<>();
        queue.add(new BTNodePair(root, rootNodeWidth));
        while(!queue.isEmpty()) {
            BTNodePair current = queue.remove();
            result.set(current.width, current.node.data);

            if(current.node.left != null) {
                queue.add(new BTNodePair(current.node.left, current.width - 1));
            }

            if(current.node.right != null) {
                queue.add(new BTNodePair(current.node.right, current.width + 1));
            }
        }
        System.out.println(result);
    }

    static void topView(BTNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return;

        int[] minMax = {0, 0};
        widthOfTreeShadowRecursion(root, 0, minMax);

        int treeWidth = minMax[1] - minMax[0] + 1;

        for(int i=0; i<treeWidth; i++) {
            result.add(null);
        }

        int rootNodeWidth = Math.abs(minMax[0]);

        Queue<BTNodePair> queue = new LinkedList<>();
        queue.add(new BTNodePair(root, rootNodeWidth));

        while(!queue.isEmpty()) {
            BTNodePair current = queue.remove();
            if(result.get(current.width) == null) {
                result.set(current.width, current.node.data);
            }

            if(current.node.left != null) {
                queue.add(new BTNodePair(current.node.left, current.width - 1));
            }

            if(current.node.right != null) {
                queue.add(new BTNodePair(current.node.right, current.width + 1));
            }
        }
        System.out.println(result);
    }

    static void leftView(BTNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return;

        Queue<BTNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {

            result.add(queue.peek().data);
            int len = queue.size();
            while(len > 0) {
                BTNode current = queue.remove();
                if(current.left != null) {
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }
                --len;
            }
        }
        System.out.println(result);
    }

    static void rightView(BTNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return;

        Queue<BTNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            result.add(queue.peek().data);
            int len = queue.size();
            while(len > 0) {
                BTNode current = queue.remove();
                // First adding right child then left child
                if(current.right != null) {
                    queue.add(current.right);
                }
                if(current.left != null) {
                    queue.add(current.left);
                }
                --len;
            }
        }
        System.out.println(result);
    }

     void leftViewUsingRecursion(BTNode node, int level) {
        // Initially max_level=-1 and level=0
        if(node == null) {
            return;
        }
        if(level > max_level) {
            System.out.print(node.data);
            this.max_level = level;
        }
        leftViewUsingRecursion(node.left, level + 1);
        leftViewUsingRecursion(node.right, level + 1);
    }

    public static void leftViewOptimized(BTNode root, int level, Map<Integer, Integer> map) {
        // https://www.techiedelight.com/print-left-view-of-binary-tree/
        if (root == null) {
            return;
        }

        // if the level is visited for the first time, insert the current node
        // and level information into the map
        map.putIfAbsent(level, root.data);

        leftViewOptimized(root.left, level + 1, map);
        leftViewOptimized(root.right, level + 1, map);
    }

    public void treeLeftView(BTNode root, List<Integer> result) {
        if(root == null) return;
        if(root.left == null && root.right == null) return;
        result.add(root.data);
        treeLeftView(root.left != null ? root.left : root.right, result);
    }

    public void treeRightView(BTNode root, List<Integer> result) {
        if(root == null) return;
        if(root.left == null && root.right == null) return;
        result.add(root.data);
        treeRightView(root.right != null ? root.right : root.left, result);
    }

    public void treeRightViewReversed(BTNode root, List<Integer> result) {
        if(root == null) return;
        if(root.left == null && root.right == null) return;
        treeRightViewReversed(root.right != null ? root.right : root.left, result);
        result.add(root.data);
    }

    public void treeLeafNode(BTNode root, List<Integer> result) {
        if(root == null) return;
        if(root.left == null && root.right == null) {
            result.add(root.data);
            return;
        }
        treeLeafNode(root.left, result);
        treeLeafNode(root.right, result);
    }

    public List<Integer> boundaryOfBinaryTree(BTNode root) {
        // https://www.lintcode.com/problem/878/
        // https://www.techiedelight.com/boundary-traversal-binary-tree/
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        result.add(root.data);
        if(root.left == null && root.right == null) return result;

        treeLeftView(root.left, result);
        treeLeafNode(root, result);
        treeRightViewReversed(root.right, result);
        return result;
    }

    static boolean isIdentical(BTNode root1, BTNode root2) {
        if(root1 == null && root2 == null) return true;

        if(root1 != null && root2 != null) {
            return (root1.data == root2.data && isIdentical(root1.left, root2.left) &&
                    isIdentical(root1.right, root2.right));
        }
        return false;
    }

    public boolean isSubtree(BTNode root, BTNode subRoot) {
        // https://leetcode.com/problems/subtree-of-another-tree
        if(root == null && subRoot == null) return true;
        if(root == null) return false;

        if(isIdentical(root, subRoot)) return true;
        if(isSubtree(root.left, subRoot)) {
            return true;
        }
        if(isSubtree(root.right, subRoot)){
            return true;
        }
        return false;
    }

    static int convertToSumTree(BTNode root) {
        if(root == null) {
            return 0;
        }
        int left_sum = convertToSumTree(root.left);
        int right_sum = convertToSumTree(root.right);
        int old_data = root.data;
        root.data = left_sum + right_sum;

        return root.data + old_data;
    }

    public static boolean getCousinPairInfo(BTNode root, BTNode target, BTNode parent,
                               CousinPair currentPair, int level) {
        if(root == null) {
            return false;
        }
        if(root == target) {
            currentPair.parent = parent;
            currentPair.level = level;
            return true;
        }
        if(getCousinPairInfo(root.left, target, root, currentPair, level+1)) {
            return true;
        }
        if(getCousinPairInfo(root.right, target, root, currentPair, level+1)) {
            return true;
        }

        return false;
    }

    public static boolean isCousinsOptimized(BTNode root, BTNode x, BTNode y) {
        // https://leetcode.com/problems/cousins-in-binary-tree/
        CousinPair currentPair1 = new CousinPair();
        boolean p1 = getCousinPairInfo(root, x, null, currentPair1, 0);

        CousinPair currentPair2 = new CousinPair();
        boolean p2 = getCousinPairInfo(root, y, null, currentPair2, 0);

        if(p1 && p2 && (currentPair1.level == currentPair2.level)) {
            return currentPair1.parent != currentPair2.parent;
        }
        return false;
    }

    public static void getCousins(BTNode root, BTNode parent, BTNode currentParent, int level,
                                  int targetLevel, List<Integer> result) {
        if(root == null) return;
        if(level == targetLevel && parent != currentParent) {
            result.add(root.data);
        }

        getCousins(root.left, parent, root, level + 1, targetLevel, result);
        getCousins(root.right, parent, root, level + 1, targetLevel, result);
    }

    public static List<Integer> findCousins(BTNode root, BTNode x)
    {
        List<Integer> result = new ArrayList<>();
        if(root == null || x == root) return result;

        CousinPair currentPair = new CousinPair();
        boolean exist = getCousinPairInfo(root, x, null, currentPair, 0);
        if(!exist) return result;

        getCousins(root, currentPair.parent, null, 0, currentPair.level, result);
        return result;
    }

    public static SumTreePair validateSumTree(BTNode root) {
        SumTreePair myPair = new SumTreePair();
        if(root == null) {
            myPair.sum = 0;
            myPair.isSumTree = true;
            return myPair;
        }
        // Leaf Node, special case
        if(root.left == null && root.right == null) {
            myPair.sum = root.data;
            myPair.isSumTree = true;
            return myPair;
        }

        SumTreePair leftPair = validateSumTree(root.left);
        SumTreePair rightPair = validateSumTree(root.right);

        myPair.sum = leftPair.sum + rightPair.sum + root.data;
        boolean current = (root.data == leftPair.sum + rightPair.sum);
        myPair.isSumTree = current && leftPair.isSumTree && rightPair.isSumTree;

        return myPair;
    }

    public static boolean isSumTreeOptimized(BTNode root) {
        // https://www.techiedelight.com/check-given-binary-tree-sum-tree-not/
        if(root == null) return true;
        return validateSumTree(root).isSumTree;
    }

    static int _sumTreesSum(BTNode node) {
        if(node == null) {
            return 0;
        }

        return (_sumTreesSum(node.left) + node.data + _sumTreesSum(node.right));
    }

    static int isSumTree(BTNode root) {
        if(root == null) {
            return 1;
        }
        // Treat leaf node as already sumTree
        if(root.left == null && root.right == null) {
            return 1;
        }

        int left_sum = _sumTreesSum(root.left);
        int right_sum = _sumTreesSum(root.right);

        if((root.data == left_sum + right_sum) && isSumTree(root.left) != 0 && isSumTree(root.right) != 0)
        {
            return 1;
        }
        return 0;
    }

    static boolean isSymmetric(BTNode node1, BTNode node2) {
        if(node1 == null && node2 == null) {
            return true;
        }

        if(node1 == null || node2 == null) {
            return false;
        }

        if(node1.data == node2.data) {
            return isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
        }
        return false;
    }

    static void convertToMirrorTree(BTNode root) {
        // Also work for inverting of Binary tree
        if(root == null) return;

        convertToMirrorTree(root.left);
        convertToMirrorTree(root.right);

        //swapping left and right node data
        BTNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public static BTNode mirrorTree(BTNode root) {
        if(root == null) return null;

        BTNode left = mirrorTree(root.right);
        BTNode right = mirrorTree(root.left);

        root.left = left;
        root.right = right;

        return root;
    }

    public static void rootToLeafPath(BTNode root, List<Integer> current, Set<List<Integer>> result) {
        if(root == null) return;

        if(root.left == null && root.right == null) {
            current.add(root.data);
            result.add(new ArrayList<Integer>(current));
            current.remove(current.size()-1);
            return;
        }

        current.add(root.data);
        rootToLeafPath(root.left, current, result);
        rootToLeafPath(root.right, current, result);
        current.remove(current.size()-1);

    }

    static void _getAllRootToLeafPath(BTNode node, int[] array, int index) {
        if(node == null) {
            return;
        }
        array[index] = node.data;

        // if leaf node
        if(node.left == null && node.right == null) {
            for(int i=0; i<=index; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
        else {
            _getAllRootToLeafPath(node.left, array, index + 1);
            _getAllRootToLeafPath(node.right, array, index + 1);
        }
    }

    static void printAllRootToLeafPath(BTNode root) {
        // We will conserve all path in 2D List and then at last print all
        int[] array = new int[100];
        int index = 0;
        _getAllRootToLeafPath(root, array, index);
    }

    static int _diffAtLevel(BTNode node, int current_sum, int current_level) {
        if(node == null) {
            return current_sum;
        }
        if(current_level % 2 == 0) {
            current_sum = current_sum - node.data;
        }
        else {
            current_sum = current_sum + node.data;
        }
        current_sum = _diffAtLevel(node.left, current_sum, current_level +1);
        current_sum = _diffAtLevel(node.right, current_sum, current_level+1);
        return current_sum;
    }

    static void diffOddAndEvenLevels(BTNode root) {
        int current_level = 1;
        int current_sum = 0;
        int final_sum = _diffAtLevel(root, current_sum, current_level);
        System.out.println("Final Diff: " + final_sum);
    }

    static int getTotalNodeCount(BTNode node) {
        if(node == null) {
            return 0;
        }
       return (1 + getTotalNodeCount(node.left) + getTotalNodeCount(node.right));
    }

    static int getHeightOfTree(BTNode node) {
        if (node == null) {
            return 0;
        }
        return (1 + Integer.max(getHeightOfTree(node.left), getHeightOfTree(node.right)));
    }

    static void isSkewedTree(BTNode node) {
        // Each node of a binary tree has exactly one child
        if(getHeightOfTree(node) == getTotalNodeCount(node)) {
            System.out.println("Tree is skewed");
        }
        else {
            System.out.println("Tree is not skewed");
        }
    }

    public static double process(String sign, double x, double y) {
        switch (sign) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                return x / y;
            case "^":
                return Math.pow(x, y);
        }
        return 0;
    }

    public static double evaluateBinaryTreeExpression(BTNode2 root) {
        // https://www.techiedelight.com/evaluate-binary-expression-tree/
        if(root == null) return 0;
        if(root.left == null && root.right == null) return Double.valueOf(root.data);
        double left = evaluateBinaryTreeExpression(root.left);
        double right = evaluateBinaryTreeExpression(root.right);

        return process(root.data, left, right);
    }

    static int treeHeightUsingEdges(BTNode root) {
        // https://www.youtube.com/watch?v=Y7fg3QS6u6w
        if(root == null) {
            return -1;
        }
        int leftHeight = treeHeightUsingEdges(root.left);
        int rightHeight = treeHeightUsingEdges(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    static int diameterOfBinaryTree(BTNode root) {
        // https://leetcode.com/problems/diameter-of-binary-tree
        // https://www.youtube.com/watch?v=S0Bwgtn32uI
        if(root == null) return 0;

        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);

        int leftHeight = treeHeightUsingEdges(root.left);
        int rightHeight = treeHeightUsingEdges(root.right);
        int currentDiameter =  2 + leftHeight + rightHeight;

        return Math.max(currentDiameter, Math.max(leftDiameter, rightDiameter));
    }

    static DiameterHeightPair diameterOfTree(BTNode root) {
        // https://www.youtube.com/watch?v=S0Bwgtn32uI
        if(root == null) {
            DiameterHeightPair basePair = new DiameterHeightPair();
            basePair.diameter = 0;
            basePair.height = -1;
            return basePair;
        }

        DiameterHeightPair leftPair = diameterOfTree(root.left);
        DiameterHeightPair rightPair = diameterOfTree(root.right);

        DiameterHeightPair myPair = new DiameterHeightPair();
        myPair.height = Math.max(leftPair.height, rightPair.height) + 1;

        int currentDiameter = leftPair.height + rightPair.height + 2;
        myPair.diameter = Math.max(currentDiameter, Math.max(leftPair.diameter, rightPair.diameter));
        return myPair;
    }

    static int diameterOfBinaryTreeOptimized(BTNode root) {
        // https://www.youtube.com/watch?v=S0Bwgtn32uI
        DiameterHeightPair resultPair = diameterOfTree(root);
        return resultPair.diameter;
    }

    static void printNodeAtGivenLevel(BTNode node, int level) {
        if(node == null) {
            return;
        }
        if(level == 1) {
            System.out.print(node.data + " ");
            return;
        }
        printNodeAtGivenLevel(node.left, level-1);
        printNodeAtGivenLevel(node.right,level-1);
    }

    static BSTPair isBST(BTNode root) {
        // https://leetcode.com/problems/validate-binary-search-tree/
        if(root == null) {
            BSTPair base = new BSTPair();
            base.min = Integer.MAX_VALUE;
            base.max = Integer.MIN_VALUE;
            base.isBST = true;
            return base;
        }

        BSTPair leftPair = isBST(root.left);
        BSTPair rightPair = isBST(root.right);

        BSTPair myPair = new BSTPair();
        myPair.isBST = leftPair.isBST && rightPair.isBST && (root.left == null || root.data > leftPair.max) &&
                (root.right == null || root.data < rightPair.min);

        myPair.min = Math.min(root.data, Math.min(leftPair.min, rightPair.min));
        myPair.max = Math.max(root.data, Math.max(leftPair.max, rightPair.max));
        return myPair;
    }

    static boolean isValidBST(BTNode root) {
        // https://www.youtube.com/watch?v=kMrbTnd5W9U
        // https://leetcode.com/problems/validate-binary-search-tree/
        BSTPair result = isBST(root);
        return result.isBST;
    }

    static LargestBSTPair largestBSTSubtree(BTNode root) {
        // https://leetcode.com/problems/validate-binary-search-tree/
        if(root == null) {
            LargestBSTPair base = new LargestBSTPair();
            base.min = Integer.MAX_VALUE;
            base.max = Integer.MIN_VALUE;
            base.isBST = true;
            base.root = null;
            base.size = 0;
            return base;
        }

        LargestBSTPair leftPair = largestBSTSubtree(root.left);
        LargestBSTPair rightPair = largestBSTSubtree(root.right);

        LargestBSTPair myPair = new LargestBSTPair();
        myPair.isBST = leftPair.isBST && rightPair.isBST && (root.left == null || root.data > leftPair.max) &&
                (root.right == null || root.data < rightPair.min);

        myPair.min = Math.min(root.data, Math.min(leftPair.min, rightPair.min));
        myPair.max = Math.max(root.data, Math.max(leftPair.max, rightPair.max));

        if(myPair.isBST) {
            myPair.size = leftPair.size + rightPair.size + 1;
            myPair.root = root;
        }
        else if(leftPair.size > rightPair.size) {
            myPair.size = leftPair.size;
            myPair.root = leftPair.root;
        }
        else {
            myPair.size = rightPair.size;
            myPair.root = rightPair.root;
        }
        return myPair;
    }

    static BalancedTreePair isBalancedTree(BTNode root) {
        if(root == null) {
            BalancedTreePair base = new BalancedTreePair();
            base.isBalanced = true;
            base.height = 0;
            return base;
        }

        BalancedTreePair leftPair = isBalancedTree(root.left);
        BalancedTreePair rightPair = isBalancedTree(root.right);

        BalancedTreePair myPair = new BalancedTreePair();
        myPair.isBalanced = leftPair.isBalanced && rightPair.isBalanced &&
                (Math.abs(leftPair.height - rightPair.height) <= 1);

        myPair.height = Math.max(leftPair.height, rightPair.height) + 1;
        return myPair;

    }

    static boolean isBalanced(BTNode root) {
        // https://leetcode.com/problems/balanced-binary-tree
        // https://leetcode.com/problems/balanced-binary-tree
        BalancedTreePair result = isBalancedTree(root);
        return result.isBalanced;
    }

    static void levelOrderTraversal(BTNode node) {
        if (node == null) {
            return;
        }
        int height = getHeightOfTree(node);
        for(int i = 0; i <= height; i++) {
            printNodeAtGivenLevel(node, i + 1);
            System.out.println();
        }
    }

    static void levelOrderTraversalUsingQueue(BTNode root) {
        if(root == null) {
            return;
        }
        Queue<BTNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            BTNode top_element = queue.remove();
            System.out.print(top_element.data + " ");
            if(top_element.left != null) queue.add(top_element.left);
            if(top_element.right != null) queue.add(top_element.right);
        }
    }

    static void levelOrderTraversalUsingQueueMethod2(BTNode root) {
        if(root == null) {
            return;
        }
        Queue<BTNode> queue = new ArrayDeque<BTNode>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                BTNode current = queue.remove();
                System.out.println(current.data + " ");
                if(current.left != null) {
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }

            }
            System.out.println();
        }
    }

    static List<Integer> findReverseLevelOrderTraversal(BTNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Stack<Integer> stack = new Stack<>();
        Queue<BTNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            BTNode top = queue.remove();
            stack.push(top.data);
            if(top.right != null) {
                queue.add(top.right);
            }
            if(top.left != null) {
                queue.add(top.left);
            }
        }

        while(!stack.isEmpty()) {
            int current = stack.pop();
            result.add(current);
        }
        return result;
    }

    static void levelOrderTraversalBetweenTwoLevels(BTNode root, int start, int end) {
        if(root == null) {
            return;
        }

        Queue<BTNode> queue = new ArrayDeque<BTNode>();
        queue.add(root);
        int level = 0;

        while(!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for(int i=0; i<size; i++) {
                BTNode current = queue.remove();
                if(level >= start && level <= end) {
                    System.out.print(current.data + " ");
                }
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            if (level >= start && level <= end) {
                System.out.println();
            }
        }
    }

    static void levelOrderSpiral(BTNode root) {
        // https://www.youtube.com/watch?v=eDdPZ05y4Os
        if(root == null) {
            return;
        }
        Stack<BTNode> mainStack = new Stack<>();
        mainStack.push(root);
        Stack<BTNode> childStack = new Stack<>();
        int level = 1;

        while(mainStack.size() > 0) {
            BTNode current = mainStack.pop();
            System.out.print(current.data + " ");

            //Go left to Right
            if(level % 2 == 1) {
                if(current.left != null) {
                    childStack.push(current.left);
                }
                if(current.right != null) {
                    childStack.push(current.right);
                }
            }
            else {
                if(current.right != null) {
                    childStack.push(current.right);
                }
                if(current.left != null) {
                    childStack.push(current.left);
                }
            }

            if (mainStack.size() == 0) {
                mainStack = childStack;
                childStack = new Stack<BTNode>();
                level++;
            }
        }
    }

    static List<Integer> SpiralOrderTraversal(BTNode root) {
        // https://www.techiedelight.com/spiral-order-traversal-binary-tree/
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        boolean flag = true; // odd = true, even = false
        Deque<BTNode> dequeue = new ArrayDeque<>();
        dequeue.addFirst(root);

        while(!dequeue.isEmpty()) {
            int size = dequeue.size();
            for(int i=0; i<size; i++) {
                if(flag) {
                    BTNode current = dequeue.pollFirst();
                    result.add(current.data);
                    if(current.left != null) {
                        dequeue.addLast(current.left);
                    }
                    if(current.right != null) {
                        dequeue.addLast(current.right);
                    }
                }
                else {
                    BTNode current = dequeue.pollLast();
                    result.add(current.data);
                    if(current.right != null) {
                        dequeue.addFirst(current.right);
                    }
                    if(current.left != null) {
                        dequeue.addFirst(current.left);
                    }
                }
            }
            flag = !flag;
        }
        return result;
    }

    static void diagonalTraversal(BTNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return;
        Queue<BTNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int length = queue.size();
            List<Integer> subResult = new ArrayList<>();
            while(length-- > 0) {
                BTNode current = queue.remove();
                while(current != null) {
                    subResult.add(current.data);
                    if(current.left != null) {
                        queue.add(current.left);
                    }
                    current = current.right;
                }
            }
            result.add(new ArrayList<>(subResult));
        }
        System.out.println(result);
    }

    public static void diagonalTraversalRecursive(BTNode root, Queue<BTNode> queue, List<Integer> result) {
        if(root == null && queue.isEmpty()) return;

        if(root == null) {
            BTNode current = queue.remove();
            diagonalTraversalRecursive(current, queue, result);
        }
        else {
            result.add(root.data);
            queue.add(root.left);
            diagonalTraversalRecursive(root.right, queue, result);
        }
    }

    public static List<Integer> findDiagonalTraversal(BTNode root) {
        // https://www.techiedelight.com/print-diagonal-traversal-binary-tree/
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<BTNode> queue = new LinkedList<>();
        diagonalTraversalRecursive(root, queue, result);
        return result;

    }

    static boolean isCousins(BTNode root, int val1, int val2) {
        if(root == null) return false;

        Queue<BTNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            boolean isVal1Present = false;
            boolean isVal2Present = false;
            for(int i=0; i<size; i++){
                BTNode current = queue.remove();
                if(current.data == val1) {
                    isVal1Present = true;
                }
                if(current.data == val2) {
                    isVal2Present = true;
                }
                if(current.left != null && current.right != null) {
                    if(current.left.data == val1 && current.right.data == val2) {
                        return false;
                    }
                    if(current.left.data == val2 && current.right.data == val1) {
                        return false;
                    }
                }
                if(current.left != null) {
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }
            }
            if(isVal1Present && isVal2Present) {
                return true;
            }
        }
        return false;
    }

//    static void getCousins(BTNode root, int val) {
//        if(root == null) return;
//
//        ArrayList<Integer> result = new ArrayList<>();
//        Queue<BTNode> queue = new LinkedList<>();
//        queue.add(root);
//
//        int sibling = Integer.MAX_VALUE;
//
//        while(!queue.isEmpty()) {
//            int size = queue.size();
//            boolean isValPresent = false;
//            for(int i=0; i<size; i++){
//                BTNode current = queue.remove();
//                if(current.data == val) {
//                    isValPresent = true;
//                }
//
//                if(current.left != null && current.right != null) {
//                    if(current.left.data == val) {
//                        sibling = current.right.data;
//                    }
//                    if(current.right.data == val) {
//                        sibling = current.left.data;
//                    }
//                }
//
//                if(isValPresent && current.data != sibling && current.data != val){
//                    result.add(current.data);
//                }
//
//                if(current.left != null) {
//                    queue.add(current.left);
//                }
//                if(current.right != null) {
//                    queue.add(current.right);
//                }
//            }
//        }
//        System.out.println(result);
//    }

    static void printCornerNodesOfTree(BTNode node) {
        if(node == null) return;
        Queue<BTNode> queue = new ArrayDeque<>();
        queue.add(node);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0; i<size; i++) {
                BTNode current = queue.remove();
                if(i == 0 || i == size-1) {
                    System.out.print(current.data + " ");
                    if(current.left != null) {
                        queue.add(current.left);
                    }
                    if(current.right != null) {
                        queue.add(current.right);
                    }
                }
            }
            System.out.println();
        }
    }

    static int maxDepthOfBinaryTree(BTNode root) {
        if(root == null) return 0;
        int maxLeft = maxDepthOfBinaryTree(root.left);
        int maxRight = maxDepthOfBinaryTree(root.right);

        return 1 + Integer.max(maxLeft, maxRight);
    }

    public static int findMinimumDepth(BTNode root) {
        // https://techiedelight.com/practice/?problem=MinimumDepthBinaryTree
        if(root == null) return 0;

        int left = findMinimumDepth(root.left);
        int right = findMinimumDepth(root.right);

        if(root.left == null) {
            return 1+right;
        }

        if(root.right == null) {
            return 1+left;
        }

        return 1 + Math.min(left, right);
    }

    static void insertNode(BTNode root, int data) {
        Queue<BTNode> queue = new ArrayDeque<BTNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BTNode current = queue.remove();
            if (current.left == null) {
                current.left = new BTNode(data);
                break;
            } else {
                queue.add(current.left);
            }
            if (current.right == null) {
                current.right = new BTNode(data);
                break;
            } else {
                queue.add(current.right);
            }
        }
    }

    static BTNode deleteNode(BTNode root, int key) {
        if(root == null) return null;

        if(root.left == null && root.right == null) {
            if(root.data == key) {
                root = null;
            }
            return root;
        }
        Queue<BTNode> queue = new ArrayDeque<BTNode>();
        BTNode keyNode = null, deepestParentNode = null;
        queue.add(root);

        while (!queue.isEmpty()) {
            boolean isParent = false;
            BTNode current = queue.remove();

            if(current.data == key) {
                keyNode = current;
            }

            if(current.left != null) {
                isParent = true;
                queue.add(current.left);
            }

            if(current.right != null) {
                isParent = true;
                queue.add(current.right);
            }

            if(isParent) deepestParentNode = current;
        }

        if(keyNode != null) {
            if(deepestParentNode.right != null) {
                keyNode.data = deepestParentNode.right.data;
                deepestParentNode.right = null;
            }
            else if(deepestParentNode.left != null) {
                keyNode.data = deepestParentNode.left.data;
                deepestParentNode.left = null;
            }
        }
        return root;
    }

    static int getRootNodeIndexUtil(int[] inOrder, int rootKey) {
        for(int i=0; i<inOrder.length; i++) {
            if(inOrder[i] == rootKey) return i;
        }
        return -1;
    }

    static void createPostOrder(int[] inOrder, int[] preOrder) {
        int rootIndex = getRootNodeIndexUtil(inOrder, preOrder[0]);

        int length = preOrder.length;

        if(rootIndex != 0) {
            // If Left Tree exist
            createPostOrder(Arrays.copyOfRange(inOrder, 0, rootIndex),
                    Arrays.copyOfRange(preOrder, 1, rootIndex+1));
        }
        if(rootIndex != length-1) {
            // If Right Tree exist
            createPostOrder(Arrays.copyOfRange(inOrder, rootIndex+1, length),
                    Arrays.copyOfRange(preOrder, rootIndex+1, length));
        }

        System.out.print(preOrder[0] + " ");
    }

    static void maxLevelSum(BTNode root) {
        if(root == null) {
            return;
        }

        int minLevel = 1;
        int maxSum = Integer.MIN_VALUE;

        int level = 1;
        Queue<BTNode> queue = new ArrayDeque<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int n = queue.size();
            int currentSum = 0;
            for(int i=0; i<n; i++) {
                BTNode current = queue.remove();
                currentSum += current.data;

                if(current.left != null) {
                    queue.add(current.left);
                }

                if(current.right != null) {
                    queue.add(current.right);
                }
            }
            if(maxSum < currentSum) {
                maxSum = currentSum;
                minLevel = level;
            }

            level++;
        }

        System.out.println("Max Sum: "+ maxSum);
        System.out.println("Min level: "+ minLevel);

    }

    public boolean nodeToRootPath(BTNode root, BTNode node, List<BTNode> path) {
        if(root == null) return false;

        if(root == node) {
            path.add(root);
            return true;
        }

        if(nodeToRootPath(root.left, node, path)) {
            path.add(root);
            return true;
        }

        if(nodeToRootPath(root.right, node, path)) {
            path.add(root);
            return true;
        }
        return false;
    }

    public BTNode lowestCommonAncestor(BTNode root, BTNode p, BTNode q) {
        // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree
        if(root == null) return null;

        List<BTNode> path_p = new ArrayList<>();
        List<BTNode> path_q = new ArrayList<>();

        nodeToRootPath(root, p, path_p);
        nodeToRootPath(root, q, path_q);

        BTNode result = null;
        int index_p = path_p.size()-1;
        int index_q = path_q.size()-1;

        while(index_p >= 0 && index_q >= 0) {
            if (path_p.get(index_p) != path_q.get(index_q)) {
                break;
            } else {
                result = path_p.get(index_p);
                index_p--;
                index_q--;
            }
        }
        return result;
    }
}

public class BinaryTreeRun {
    static BTNode manualTree1() {
        BTNode root = new BTNode(50);
        root.left = new BTNode(25);
        root.right = new BTNode(75);
        root.left.left = new BTNode(12);
        root.left.right = new BTNode(37);
        root.right.left = new BTNode(62);
        root.right.right = new BTNode(87);
        root.left.right.left = new BTNode(30);
        root.right.left.right = new BTNode(70);

        //           1
        //      /          \
        //     2            3
        //   / \          /
        //   4   5        6
        //      / \      / \
        //     7   8    9  10

        // BTNode root = new BTNode(1);
        // root.left = new BTNode(2);
        // root.right = new BTNode(3);
        // root.left.left = new BTNode(4);
        // root.left.right = new BTNode(5);
        // root.left.right.left = new BTNode(7);
        // root.left.right.right = new BTNode(8);
        // root.right.left = new BTNode(6);
        // root.right.left.left = new BTNode(9);
        // root.right.left.right = new BTNode(10);
        return root;
    }

    public static void main(String[] args) {
        BinaryTree BT = new BinaryTree();
//        BTNode root = BT.createTree();
        System.out.println(BinaryTree.widthOfTreeShadow(manualTree1()));
    }
}

