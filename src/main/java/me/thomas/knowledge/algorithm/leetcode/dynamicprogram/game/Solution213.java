package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game;

/**
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution213.java, v 0.1 2022/8/30 22:00 xinsheng2.zhao Exp $
 */
public class Solution213 {

    /**
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     * <p>
     * 思路：分成两个子数组去偷，1个包含开头不包含末尾，另一个反之。
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = robRange(nums, 0, nums.length);
        if (dp[nums.length - 2] == dp[nums.length - 1]) { /* 最后一个房子没有偷 */
            return dp[nums.length - 2];
        }

        int[] dp2 = robRange(nums, 1, nums.length);
        return Math.max(dp[nums.length - 2], dp2[nums.length - 2]);
    }

    int[] robRange(int[] nums, int start, int end) {
        int[] dp = new int[end - start + 1];
        dp[0] = nums[start];
        dp[1] = Math.max(dp[0], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i - start] = Math.max(nums[i] + dp[i - start - 2], dp[i - start - 1]);
        }
        return dp;
    }
}
