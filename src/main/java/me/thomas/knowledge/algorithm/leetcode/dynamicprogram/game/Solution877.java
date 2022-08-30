package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game;

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
     * <p>
     * 思路：这是符合人类思维的思路，使用Pair分别记录第一个人和第二个人选择的最优的石子堆的分数之和。
     * dp[i][j]的含义是数组范围在[i,j]内，两选手拿到的最优分数之和分别为dp[i][j].fir,dp[i][j].sec。
     * 如果先手方先拿nums[i]，那么剩下的数组范围为[i + 1, j]，此时先手方变成了后手，只能被动选择[i + 1, j].sec，因为对于数组范围[i + 1, j]来说，原后手方变成了先手方，
     * 他的选择肯定是[i + 1, j].fir。（都发挥出最佳水平的情况下，任意长度的数组，其fir的值肯定大于sec。）
     * 对于每一个[i,j]，在循环体中都会分别模拟先后手做选择 -- 即对fir和sec都赋值，所以说这个思路符合人类思维，虽然其不是代码最简洁的版本。
     */
    public boolean stoneGame(int[] piles) {
        if (piles == null || piles.length == 0) {
            return true;
        }

        int size = piles.length;
        Pair[][] dp = new Pair[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }

        for (int i = 0; i < size; i++) {
            dp[i][i].fir = piles[i];
        }

        for (int i = size - 2; i >= 0; i--) {
            for (int j = i + 1; j < size; j++) {
                // 分别计算选择左边和选择右边的得分
                int left = piles[i] + dp[i + 1][j].sec; /* 如果先手方先拿nums[i]，那么剩下的数组范围为[i + 1, j]，此时先手方变成了后手，只能被动选择[i + 1, j].sec */
                int right = piles[j] + dp[i][j - 1].sec;

                if (left > right) {
                    dp[i][j].fir = left;
                    dp[i][j].sec = dp[i + 1][j].fir;
                } else {
                    dp[i][j].fir = right;
                    dp[i][j].sec = dp[i][j - 1].fir;
                }
            }
        }

        return dp[0][size - 1].fir > dp[0][size - 1].sec;
    }

    static class Pair {
        int fir, sec;

        public Pair(int fir, int sec) {
            this.fir = fir;
            this.sec = sec;
        }
    }

    public static void main(String[] args) {
        Solution877 solution = new Solution877();
        System.out.println(solution.stoneGame(new int[] { 5, 3, 4, 5 }));
    }
}
