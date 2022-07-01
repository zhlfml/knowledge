package me.thomas.knowledge.algorithm.leetcode.dynamicprogram;

/**
 * Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i]。
 * <p>
 * 游戏以谁手中的石子最多来决出胜负。石子的 总数 是 奇数 ，所以没有平局。
 * <p>
 * Alice 和 Bob 轮流进行，Alice 先开始 。 每回合，玩家从行的 开始 或 结束 处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中 石子最多 的玩家 获胜 。
 * <p>
 * 假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回true，当 Bob 赢得比赛时返回false。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution877.java, v 0.1 2022/7/1 16:26 xinsheng2.zhao Exp $
 */
public class Solution877 {

    /**
     * 输入：piles = [5,3,4,5]
     * 输出：true
     * 解释：
     * Alice 先开始，只能拿前 5 颗或后 5 颗石子 。
     * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
     * 如果 Bob 拿走前 3 颗，那么剩下的是 [4,5]，Alice 拿走后 5 颗赢得 10 分。
     * 如果 Bob 拿走后 5 颗，那么剩下的是 [3,4]，Alice 拿走后 4 颗赢得 9 分。
     * 这表明，取前 5 颗石子对 Alice 来说是一个胜利的举动，所以返回 true 。
     */
    public boolean stoneGame(int[] piles) {
        if (piles == null || piles.length == 0) {
            return true;
        }

        int[] dp = new int[piles.length];
        for (int i = piles.length - 1; i >= 0; i--) {
            int[] tmp = new int[piles.length];
            for (int j = i; j < piles.length; j++) {
                if (i == j) {
                    tmp[j] = piles[j];
                } else {
                    tmp[j] = Math.max(piles[i] - dp[j], piles[j] - tmp[j - 1]);
                }
            }
            dp = tmp;
        }

        return dp[piles.length - 1] > 0;
    }

    public static void main(String[] args) {
        Solution877 solution = new Solution877();
        System.out.println(solution.stoneGame(new int[] { 5, 3, 4, 5 }));
    }
}
