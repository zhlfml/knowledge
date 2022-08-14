package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.knapsack;

/**
 * 518. 零钱兑换 II
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * <p>
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * <p>
 * 假设每一种面额的硬币有无限个。
 * <p>
 * 题目数据保证结果符合 32 位带符号整数。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution518.java, v 0.1 2022/8/11 21:27 xinsheng2.zhao Exp $
 */
public class Solution518 {

    /**
     * 输入：amount = 5, coins = [1, 2, 5]
     * 输出：4
     * 解释：有四种方式可以凑成总金额：
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1]; /* 含义：使用前i个硬币可以组成金额j的可能性有dp[i][j]种 */
        dp[0] = 1; /* 组成金额为0只有一种方式 */
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i - 1]) {
                    dp[j] += dp[j - coins[i - 1]];
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        Solution518 solution = new Solution518();
        System.out.println(solution.change(5, new int[] { 1, 2, 5 }));
    }
}
