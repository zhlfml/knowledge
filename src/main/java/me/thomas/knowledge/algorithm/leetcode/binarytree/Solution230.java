package me.thomas.knowledge.algorithm.leetcode.binarytree;

import me.thomas.knowledge.algorithm.leetcode.TreeNode;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution230.java, v 0.1 2022/10/22 23:55 xinsheng2.zhao Exp $
 */
public class Solution230 {


    int pos    = 1; /* 当前打印节点的升序位置，题目要求从1开始 */
    int answer = 0;
    int visited = 0; /* 访问的节点数，测试用 */

    /**
     * 对BST来说，中序遍历就是升序排序
     */
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
        }
        dfs(root, k);
        System.out.println(visited);
        return answer;
    }

    void dfs(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        visited++;
        dfs(node.left, k);
        if (pos > k) { /* 经测试，这里加上退出判断，的确可以减少后续节点的访问数量 */
            return;
        }
        if (pos++ == k) {
            answer = node.val;
            return;
        }
        dfs(node.right, k);
    }
}
