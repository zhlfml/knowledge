package me.thomas.knowledge.algorithm.leetcode.dynamicprogram;

/**
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组 是数组中的一个连续部分。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution53.java, v 0.1 2022/8/9 20:32 xinsheng2.zhao Exp $
 */
public class Solution53 {

    /**
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6。
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int answer = nums[0];
        int[] dp = new int[nums.length]; /* 含义：从0到i（数组和必须包含num[i]）的最大数组和为dp[i] */
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }
}
