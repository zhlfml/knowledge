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

    final int UNSET = -2;

    public int coinChange(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, UNSET);
        return helper(coins, amount, memo);
    }

    int helper(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] > UNSET) {
            return memo[amount];
        }
        int best = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subAnswer = helper(coins, amount - coin, memo);
            if (subAnswer >= 0) {
                best = Math.min(best, subAnswer);
            }
        }
        memo[amount] = (best == Integer.MAX_VALUE) ? -1 : 1 + best;
        return memo[amount];
    }
}
