package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game.stock;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution309.java, v 0.1 2022/9/8 22:42 xinsheng2.zhao Exp $
 */
public class Solution309 {

    /**
     * 输入: prices = [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[][] dp = new int[n][2]; /* 含义：第i天持股状态为j的最大收益为dp[i][j] */
        // base case: 头两天特殊处理
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(dp[0][1], -prices[1]);
        for (int i = 2; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]); /* 第i - 1天买入，第i天可以卖出 */
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]); /* 第i天买入的前提是第i - 2天或更早卖出 */
        }
        return dp[n - 1][0];
    }

    public static void main(String[] args) {
        Solution309 solution = new Solution309();
        System.out.println(solution.maxProfit(new int[] { 1, 6, 1, 2, 5 })); // output 8 not 9
    }
}
