package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.knapsack;

import java.util.Arrays;

/**
 * 805. 数组的均值分割
 * 给定你一个整数数组 nums
 * <p>
 * 我们要将 nums 数组中的每个元素移动到 A 数组 或者 B 数组中，使得 A 数组和 B 数组不为空，并且 average(A) == average(B) 。
 * <p>
 * 如果可以完成则返回true ， 否则返回 false  。
 * <p>
 * 注意：对于数组 arr ,  average(arr) 是 arr 的所有元素的和除以 arr 长度。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution805.java, v 0.1 2022/11/14 23:16 xinsheng2.zhao Exp $
 */
public class Solution805 {

    /**
     * 思路：动态规划之背包问题
     * 求出所有值的平均数，也即等于数组A和B的平均数。然后求子背包为整数n，其和为平均数乘以n。如果存在返回true，不存在返回false。
     * 还可以考虑先排序。
     */
    public boolean splitArraySameAverage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int m = nums.length;
        int sum = Arrays.stream(nums).sum();
        double avg = (double) sum / m;
        for (int i = 1; i < m; i++) {
            if (bag(nums, i, (int) (avg * i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测是否存在从nums中取n个元素，其和正好为sum。
     */
    boolean bag(int[] nums, int n, int sum) {
        int m = nums.length;
        boolean[][][] dp = new boolean[m + 1][n + 1][sum + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0][0] = true; // base case: 没有元素可以获取时，其和为0。
        }
        for (int i = 1; i <= m; i++) { // 从第一个元素开始选择，直到第m个。
            for (int j = 1; j <= n; j++) {
                if (i < j) {
                    break;
                }
                for (int k = 0; k <= sum; k++) { // 重量范围: [0, sum]
                    if (k < nums[i - 1]) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k] || dp[i - 1][j - 1][k - nums[i - 1]];
                    }
                }
            }
        }
        return dp[m][n][sum];
    }

    public static void main(String[] args) {
        Solution805 solution805 = new Solution805();
        System.out.println(solution805.splitArraySameAverage(new int[] { 2, 0, 7, 0, 6 }));
    }
}
