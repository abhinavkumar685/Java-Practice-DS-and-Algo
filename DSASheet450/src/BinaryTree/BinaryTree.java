package BinaryTree;

import java.util.*;

class DiameterPair {
    int height;
    int diameter;
}

class TraversalPair {
    Node node;
    int status;

    TraversalPair(Node node, int val) {
        this.node = node;
        this.status = val;
    }
}

class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}

public class BinaryTree {
    static ArrayList <Integer> levelOrderTraversal(Node node) {
        ArrayList<Integer> result = new ArrayList<>();
        if(node == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i=0; i<len; i++) {
                Node current = queue.remove();
                result.add(current.data);
                if(current.left != null) {
                    queue.add(current.left);
                }
                if(current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return result;
    }

    static ArrayList<Integer> reverseLevelOrderTraversal(Node node) {
        ArrayList<Integer> result = new ArrayList<>();
        if(node == null) return result;
        Stack<Integer> stack = new Stack<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i=0; i<len; i++) {
                Node current = queue.remove();
                stack.push(current.data);
                if(current.right != null) {
                    queue.add(current.right);
                }
                if(current.left != null) {
                    queue.add(current.left);
                }
            }
        }
        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    static int height(Node node) {
        if(node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    DiameterPair diameterOfTree(Node root) {
        // Diameter based on node count
        DiameterPair myPair = new DiameterPair();
        if(root == null) {
            myPair.height = 0;
            myPair.diameter = 0;
            return myPair;
        }

        DiameterPair leftPair = diameterOfTree(root.left);
        DiameterPair rightPair = diameterOfTree(root.right);

        int currentHeight = Math.max(leftPair.height, rightPair.height) + 1;
        int currentDiameter = leftPair.height + rightPair.height + 1;

        myPair.height = currentHeight;
        myPair.diameter = Math.max(currentDiameter, Math.max(leftPair.diameter,
                rightPair.diameter));
        return myPair;
    }

    int diameterBasedOnNodeCount(Node root) {
        if(root == null) return 0;
        DiameterPair result = diameterOfTree(root);
        return result.diameter;
    }

    public Node createMirrorTree(Node root) {
        if(root == null) return null;
        Node mirror = new Node(root.data);
        mirror.right = createMirrorTree(root.left);
        mirror.left = createMirrorTree(root.right);
        return mirror;
    }

    public static List<Integer> findInorderIterativeTraversal(Node root) {
        Stack<TraversalPair> stack = new Stack<>();
        TraversalPair current = new TraversalPair(root, 1);
        stack.push(current);

        List<Integer> inorder = new ArrayList<>();
        List<Integer> preorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        if(root == null) {
            return inorder;
        }

        while(!stack.isEmpty()) {
            current = stack.peek();
            if(current.status == 1) {
                preorder.add(current.node.data);
                current.status = 2;
                if(current.node.left != null) {
                    stack.push(new TraversalPair(current.node.left, 1));
                }
            }
            else if(current.status == 2) {
                inorder.add(current.node.data);
                current.status = 3;
                if(current.node.right != null) {
                    stack.push(new TraversalPair(current.node.right, 1));
                }
            }
            else {
                postorder.add(current.node.data);
                stack.pop();
            }
        }
        return inorder;
    }

    public static void main(String[] args) {

    }
}
