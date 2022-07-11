package me.thomas.knowledge.algorithm.leetcode;

/**
 * TreeNode
 *
 * @author xinsheng2.zhao
 * @version Id: TreeNode.java, v 0.1 2022/7/10 19:26 xinsheng2.zhao Exp $
 */
public class TreeNode {
    public int      val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
