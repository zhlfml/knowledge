package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.knapsack;

/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution416.java, v 0.1 2022/8/10 21:09 xinsheng2.zhao Exp $
 */
public class Solution416 {

    /**
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     */
    public boolean canPartition(int[] nums) {
        int weightSum = 0;
        for (int weight : nums) {
            weightSum += weight;
        }
        if (weightSum % 2 == 1) {
            return false;
        }

        int avg = weightSum / 2;
        boolean[] dp = new boolean[avg + 1]; /* 含义：前i个物品（取任意个）能否放满重量为j的背包 */
        // 最本质的base case
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = avg; j >= 1; j--) {
                if (j >= nums[i - 1]) {
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                }
            }
        }
        return dp[avg];
    }

    public static void main(String[] args) {
        Solution416 solution = new Solution416();
        System.out.println(solution.canPartition(new int[] { 1, 5, 2 }));
    }
}
