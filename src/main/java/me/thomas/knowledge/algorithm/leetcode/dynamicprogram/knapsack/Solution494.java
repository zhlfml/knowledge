package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.knapsack;

import java.util.Arrays;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * <p>
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * <p>
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution494.java, v 0.1 2022/8/11 23:20 xinsheng2.zhao Exp $
 */
public class Solution494 {

    /**
     * 思路：设加法的集合为A，减法的集合为B，则A-B=target。
     * 由于A+B=sum，所以A=(sum+target)/2，问题变成了有多少个子集其和为(sum+target)/2。
     * <p>
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if (sum < Math.abs(target)) {
            return 0;
        }
        int total = sum + target;
        if (total % 2 > 0) {
            return 0;
        }
        int len = nums.length;
        int half = total / 2;
        int[] dp = new int[half + 1]; /* 含义：nums前i个数凑成数字j的方案数为dp[i][j] */
            dp[0] = 1; /* 前i个数字凑成0的方案数为1 */
        for (int i = 1; i <= len; i++) {
            for (int j = half; j >= 0; j--) { /* 由于dp[j]依赖dp[j-nums[i-1]], 如果j从小到大遍历，那么dp[j-nums[i-1]]当前行计算时会被覆盖导致读取的是当前行的值，所以需要改成j从大到小遍历。 */
                if (j >= nums[i - 1]) {
                    dp[j] += dp[j - nums[i - 1]];
                }
            }
        }
        return dp[half];
    }

    public static void main(String[] args) {
        Solution494 solution = new Solution494();
        System.out.println(solution.findTargetSumWays(new int[] { 1, 1, 1, 1, 1 }, 3));
    }
}
