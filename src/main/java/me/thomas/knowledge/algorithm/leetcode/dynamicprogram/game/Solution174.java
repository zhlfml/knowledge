package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game;

/**
 * 174. 地下城游戏
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 * <p>
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 * <p>
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 * <p>
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution174.java, v 0.1 2022/8/14 21:34 xinsheng2.zhao Exp $
 */
public class Solution174 {

    /**
     * 思路：骑士行走过程中健康点不能小于0，所以走到(i,j)时需要记录两个状态 -- 1. 保持不死的最小健康点数 2. 走最小健康点数路径的剩余健康点数。
     *
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) {
            return 0;
        }
        int m = dungeon.length, n = dungeon[0].length;
        // 根据思路，需要两个二维数组，所以定义成首位为2的三位数组。
        int[][][] dp = new int[2][m][n]; /* 含义：从(0,0)走到(i,j)需要的健康点数为dp[i][j] */
        // base case: start point
        dp[0][0][0] = Math.min(dungeon[0][0], 0); /* 需要最小健康点数, 存的值可能是负数或0 */
        dp[1][0][0] = dungeon[0][0]; /* 走最小健康点数路径的剩余健康点数，存的值正负数都有可能 */
        // base case : first row
        for (int j = 1; j < n; j++) {
            dp[0][0][j] = Math.min(dp[0][0][j - 1], dp[0][0][j - 1] + dungeon[0][j]);
            dp[1][0][j] = dp[1][0][j - 1] + dungeon[0][j];
        }
        // base case : first column
        for (int i = 1; i < m; i++) {
            dp[0][i][0] = Math.min(dp[0][i - 1][0], dp[0][i - 1][0] + dungeon[i][0]);
            dp[1][i][0] = dp[1][i - 1][0] + dungeon[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 哪条路需要的健康点数少，就走哪条路
                if (dp[0][i - 1][j] >= dp[0][i][j - 1]) {
                    dp[1][i][j] = dp[1][i - 1][j] + dungeon[i][j];
                    dp[0][i][j] = Math.min(dp[0][i - 1][j], dp[1][i][j]); /* 关键是和当前剩余的健康点数比较 */
                } else {
                    dp[1][i][j] = dp[0][i][j - 1] + dungeon[i][j];
                    dp[0][i][j] = Math.min(dp[0][i][j - 1], dp[1][i][j]);
                }
            }
        }
        return Math.max(1 - dp[0][m - 1][n - 1], 0);
    }

    public static void main(String[] args) {
        Solution174 solution = new Solution174();
        System.out.println(solution.calculateMinimumHP(new int[][] { { 0, 0, 0 }, { 1, 1, -1 } }));
    }
}
