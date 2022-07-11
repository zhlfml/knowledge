package me.thomas.knowledge.algorithm.leetcode.binarytree;

import me.thomas.knowledge.algorithm.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 889. 根据前序和后序遍历构造二叉树
 * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
 * <p>
 * 如果存在多个答案，您可以返回其中 任何 一个。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution889.java, v 0.1 2022/7/11 21:41 xinsheng2.zhao Exp $
 */
public class Solution889 {

    /**
     * 输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
     * 输出：[1,2,3,4,5,6,7]
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        if ((preorder == null || preorder.length == 0) || (postorder == null || postorder.length == 0)) {
            return null;
        }
        Map<Integer, Integer> preorderMap = new HashMap<>((int) (preorder.length / .75) + 1);
        for (int i = 0; i < preorder.length; i++) {
            preorderMap.put(preorder[i], i);
        }
        return constructFromPrePost(preorder, 0, preorder.length, postorder, 0, postorder.length, preorderMap);
    }

    /**
     * 从前序与后序遍历序列构造二叉树
     *
     * @param preorder    前序遍历集合
     * @param preStart    前序遍历集合开始处，包含
     * @param preEnd      前序遍历集合结束处，不包含
     * @param postorder   后序遍历集合
     * @param postStart   后序遍历集合开始处，包含
     * @param postEnd     后序遍历集合结束处，不包含
     * @param preorderMap 前序遍历数值和位置的映射
     * @return 二叉树
     */
    private TreeNode constructFromPrePost(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd, Map<Integer, Integer> preorderMap) {
        if (preStart == preEnd) {
            return null;
        }
        int rootVal = postorder[postEnd - 1];
        TreeNode root = new TreeNode(rootVal);
        if (postEnd - postStart > 1) {
            int rightChildIndex = preorderMap.get(postorder[postEnd - 2]);
            root.left = constructFromPrePost(preorder, preStart + 1, rightChildIndex, postorder, postStart, postStart + (rightChildIndex - (preStart + 1)), preorderMap);
            root.right = constructFromPrePost(preorder, rightChildIndex, preEnd, postorder, postStart + (rightChildIndex - (preStart + 1)), postEnd - 1, preorderMap);
        }
        return root;
    }

    public static void main(String[] args) {
        Solution889 solution = new Solution889();
        TreeNode root = solution.constructFromPrePost(new int[] { 1, 2, 4, 5, 3, 6, 7 }, new int[] { 4, 5, 2, 6, 7, 3, 1 });
        System.out.println(root.val);
    }
}
