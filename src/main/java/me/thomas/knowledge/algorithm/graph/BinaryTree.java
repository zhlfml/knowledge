package me.thomas.knowledge.algorithm.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 *
 * @author zhaoxinsheng
 * @date 6/12/16.
 */
public class BinaryTree {

    private Node root;

    /**
     * 构造二叉树
     */
    public BinaryTree() {
        Node i = new Node(null, null, "I");
        Node h = new Node(null, null, "H");
        Node g = new Node(null, null, "G");
        Node f = new Node(i, null, "F");
        Node e = new Node(h, null, "E");
        Node d = new Node(null, g, "D");
        Node c = new Node(null, f, "C");
        Node b = new Node(d, e, "B");
        root = new Node(b, c, "A");
    }

    /**
     * 深度优先遍历/Depth-First Search
     */
    public void dfs(Node node) {
        if (node != null) {
            System.out.println("Visit: " + node.getName());
            dfs(node.getLeft());
            dfs(node.getRight());
        }
    }

    /**
     * 广度优先遍历/Breadth-First Search
     */
    public void bfs(Node root, Queue<Node> queue) {
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println("Visit: " + node.getName());
            Node left = node.getLeft();
            Node right = node.getRight();
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }
    }


    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        System.out.println("DFS:");
        tree.dfs(tree.root);
        System.out.println("BFS:");
        tree.bfs(tree.root, new LinkedList<Node>());
    }
}
