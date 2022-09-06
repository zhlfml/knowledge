package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game.stock;

/**
 * 123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution123.java, v 0.1 2022/9/5 21:49 xinsheng2.zhao Exp $
 */
public class Solution123 {

    /**
     * 输入：prices = [3,3,5,0,0,3,1,4]
     * 输出：6
     * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     */
    public int maxProfit(int[] prices) {
        int[][][] dp = new int[prices.length][3][2]; /* 含义：第i天最多买入过j次股票当前手里是否持股的状态为k的最大收益为dp[i][j][k] */
        /*
            base case: 第一天没有交易的前提下未持仓的收益为0，持仓的收益为负买入价.
            其实dp[x][0][0] = 0, dp[x][0][1] = -infinity，但是由于数组初始化的值为0，且动态规划状态转移方程不依赖dp[x][0][1]，所以没有必要显示对这两种情况赋初值。
         */
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j < 3; j++) {
                dp[i][j][0] = 0;
                dp[i][j][1] = -prices[0];
            }
        }

        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j < 3; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                System.out.printf("dp[%d][%d][%d]=%d\t", i, j, 0, dp[i][j][0]);
                System.out.printf("dp[%d][%d][%d]=%d\n", i, j, 1, dp[i][j][1]);
            }
        }

        return dp[prices.length - 1][2][0];
    }

    public static void main(String[] args) {
        Solution123 solution = new Solution123();
        System.out.println(solution.maxProfit(new int[] { 1, 2, 3, 4, 5 }));
    }
}
