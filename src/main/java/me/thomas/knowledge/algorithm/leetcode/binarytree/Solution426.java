package me.thomas.knowledge.algorithm.leetcode.binarytree;

/**
 * 426. 将二叉搜索树转化为排序的双向链表*
 *
 * @author xinsheng2.zhao
 * @version Id: Solution426.java, v 0.1 2023/1/2 11:37 xinsheng2.zhao Exp $
 */
public class Solution426 {

    Node head, prev;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        inorder(root);
        head.left = prev;
        prev.right = head;
        return head;
    }

    void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        if (head == null) {
            head = node;
        }
        if (prev != null) {
            prev.right = node;
        }
        node.left = prev;
        prev = node;
        inorder(node.right);
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(5);

        Solution426 solution426 = new Solution426();
        Node answer = solution426.treeToDoublyList(root);
        while (answer != null) {
            System.out.print(answer.val + " ");
            answer = answer.right;
        }
    }
}

class Node {
    int val;
    Node left;
    Node right;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
