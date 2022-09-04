package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game.sock;

/**
 * 122. 买卖股票的最佳时机 II
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * <p>
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * <p>
 * 返回 你能获得的 最大 利润 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution122.java, v 0.1 2022/9/4 17:21 xinsheng2.zhao Exp $
 */
public class Solution122 {

    /**
     * 题目描述
     * 评论 (2.3k)
     * 题解 (3.9k)
     * 提交记录
     * 122. 买卖股票的最佳时机 II
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     * <p>
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * <p>
     * 返回 你能获得的 最大 利润 。
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        return maxProfit_greedy(prices);
    }

    /**
     * 思路：贪心法
     * 每天都买卖一次，如果是正收益就算入总额，负收益则当做没有此次交易。
     */
    int maxProfit_greedy(int[] prices) {
        int answer = 0;
        for (int i = 1; i < prices.length; i++) {
            answer += Math.max(prices[i] - prices[i - 1], 0);
        }
        return answer;
    }
}