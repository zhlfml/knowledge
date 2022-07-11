package me.thomas.knowledge.algorithm.leetcode.binarytree;

import me.thomas.knowledge.algorithm.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution106.java, v 0.1 2022/7/11 20:17 xinsheng2.zhao Exp $
 */
public class Solution106 {

    /**
     * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * 输出：[3,9,20,null,null,15,7]
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if ((inorder == null || inorder.length == 0) || (postorder == null || postorder.length == 0)) {
            return null;
        }
        Map<Integer /* val */, Integer /* index */> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length, postorder, 0, postorder.length, inorderMap);
    }

    /**
     * 从中序与后序遍历序列构造二叉树
     *
     * @param inorder    中序遍历集合
     * @param inStart    中序遍历集合开始处，包含
     * @param inEnd      中序遍历集合结束处，不包含
     * @param postorder  后序遍历集合
     * @param postStart  后序遍历集合开始处，包含
     * @param postEnd    后序遍历集合结束处，不包含
     * @param inorderMap 中序遍历数值和位置的映射
     * @return 二叉树
     */
    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, Map<Integer, Integer> inorderMap) {
        int rootVal = postorder[postEnd - 1];
        int inorderIndex = inorderMap.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        if (inorderIndex > inStart) {
            root.left = buildTree(inorder, inStart, inorderIndex, postorder, postStart, postStart + inorderIndex - inStart, inorderMap);
        }
        if (inorderIndex < inEnd - 1) {
            root.right = buildTree(inorder, inorderIndex + 1, inEnd, postorder, postStart + inorderIndex - inStart, postEnd - 1, inorderMap);
        }
        return root;
    }

    public static void main(String[] args) {
        Solution106 solution = new Solution106();
        TreeNode root = solution.buildTree(new int[] { 9, 3, 15, 20, 7 }, new int[] { 9, 15, 7, 20, 3 });
        System.out.println(root.val);
    }
}
