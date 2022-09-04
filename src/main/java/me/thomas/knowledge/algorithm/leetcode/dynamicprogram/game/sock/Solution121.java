package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game.sock;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution121.java, v 0.1 2022/9/1 21:51 xinsheng2.zhao Exp $
 */
public class Solution121 {

    /**
     * 总结：贪心法可以解决的问题一定可以使用动态规划解决。而且贪心的思路和dp的含义有相同之处。
     * 比如这题贪心之处在于有一个变量始终保存当前的最大收益，有一个变量始终保存当前的最小买入价格，而动态规划的dp的含义与此不谋而合。
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        return maxProfit_dp(prices);
    }

    /**
     * 思路：贪心法
     * 当发现更低的买入价后，抛出上一个周期的股票并计算收入。
     * 遍历完所有日期的股价后，算出收益最高的价值返回。
     */
    int maxProfit_greedy(int[] prices) {
        int maxProfitSoFar = 0;
        int lowestPrice = prices[0], highestPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (lowestPrice > prices[i]) { /* 遇到了更低价，准备买入 */
                maxProfitSoFar = Math.max(maxProfitSoFar, highestPrice - lowestPrice);
                lowestPrice = prices[i];
                highestPrice = prices[i];
            } else if (highestPrice < prices[i]) {
                highestPrice = prices[i];
            }
        }
        return Math.max(maxProfitSoFar, highestPrice - lowestPrice);
    }

    /**
     * 思路：动态规划
     * 状态有两种：1.日期（即变量i） 2.持有状态 -- 0.不持有股票 1.持有股票
     * 遍历这两种状态做选择 -- 持有时卖出或观望，不持有时买入或观望，比较哪种收益更高。
     */
    int maxProfit_dp(int[] prices) {
        int[][] dp = new int[prices.length][2]; /* 含义：第i天持有股票或不持有股票的最大收益分别是dp[i][1], dp[i][0] */
        // base case
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]); /* 当日手里没有股票的最高收益要么是之前的最高收益，要么是以今天的价格抛出后的最高收益 */
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]); /* 始终持有买入价格最低的股票 */
        }
        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {
        Solution121 solution = new Solution121();
        System.out.println(solution.maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
    }
}
