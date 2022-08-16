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
     * 思路：逆向思维，公主找骑士。
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
        int[][] dp = new int[m][n]; /* 含义：从(i,j)走到(m-1,n-1)需要的健康点数为dp[i][j] */
        // base case: start point
        dp[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);
        // base case : last row
        for (int j = n - 2; j >= 0; j--) {
            dp[m - 1][j] = Math.max(dp[m - 1][j + 1] - dungeon[m - 1][j], 1);
        }
        // base case : last column
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = Math.max(dp[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        Solution174 solution = new Solution174();
        System.out.println(solution.calculateMinimumHP(new int[][] { { -3, 5 } }));
    }
}
