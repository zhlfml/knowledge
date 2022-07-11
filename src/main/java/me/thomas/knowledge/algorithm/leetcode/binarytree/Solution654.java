package me.thomas.knowledge.algorithm.leetcode.binarytree;

import me.thomas.knowledge.algorithm.leetcode.TreeNode;

/**
 * 654. 最大二叉树
 * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
 * <p>
 * 创建一个根节点，其值为 nums 中的最大值。
 * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
 * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
 * 返回 nums 构建的 最大二叉树 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution654.java, v 0.1 2022/7/11 21:11 xinsheng2.zhao Exp $
 */
public class Solution654 {

    /**
     * 输入：nums = [3,2,1,6,0,5]
     * 输出：[6,3,5,null,2,0,null,null,1]
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }

    /**
     * 构建最大二叉树
     *
     * @param nums  数组
     * @param start 数组范围从，包含
     * @param end   数组范围到，不包含
     * @return 二叉树
     */
    private TreeNode constructMaximumBinaryTree(int[] nums, int start, int end) {
        int maxIndex = maximumIndex(nums, start, end);
        TreeNode root = new TreeNode(nums[maxIndex]);
        if (maxIndex > start) {
            root.left = constructMaximumBinaryTree(nums, start, maxIndex);
        }
        if (maxIndex < end - 1) {
            root.right = constructMaximumBinaryTree(nums, maxIndex + 1, end);
        }
        return root;
    }

    /**
     * 范围内最大值所在的位置
     *
     * @param nums  数组
     * @param start 数组区间 从，包含
     * @param end   数组区间 到，不包含
     * @return 区间内最大值的下标
     */
    private int maximumIndex(int[] nums, int start, int end) {
        int max = start;
        for (int i = start + 1; i < end; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution654 solution = new Solution654();
        solution.constructMaximumBinaryTree(new int[] { 3, 2, 1, 6, 0, 5 });
    }
}
