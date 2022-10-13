package me.thomas.knowledge.algorithm.leetcode.dynamicprogram;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution322.java, v 0.1 2022/8/3 22:08 xinsheng2.zhao Exp $
 */
public class Solution322 {

    final int INF = 0x3fffffff; /* 使用INF作为无穷大还是有优势的，可以避免数字越界 */

    final int UNSET = -2;

    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, UNSET);
        int answer = helper(coins, amount, memo);
        return answer < INF ? answer : -1;
    }

    int helper(int[] coins, int amount, int[] memo) {
        if (amount < 0) {
            return INF; /* 表示无解 */
        }
        if (amount == 0) {
            return 0;
        }
        if (memo[amount] != UNSET) {
            return memo[amount];
        }
        int answer = INF;
        for (int coin : coins) {
            answer = Math.min(answer, helper(coins, amount - coin, memo) + 1);
        }
        memo[amount] = answer;
        return answer;
    }
}
