package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game.stock;

/**
 * 188. 买卖股票的最佳时机 IV
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution188.java, v 0.1 2022/9/7 21:43 xinsheng2.zhao Exp $
 */
public class Solution188 {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int n = prices.length;
        int[][][] dp = new int[n][k + 1][2]; /* 含义：第i天最多买入过j次股票当前手里是否持股的状态为k的最大收益为dp[i][j][k] */
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = 0;
                dp[i][j][1] = -prices[i];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }

}
