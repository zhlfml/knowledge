package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution64.java, v 0.1 2022/8/14 21:17 xinsheng2.zhao Exp $
 */
public class Solution64 {

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n]; /* 含义：从(0,0)走到(i,j)的最小路径和为dp[i][j] */
        // base case: start point
        dp[0][0] = grid[0][0];
        // base case: first row
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        // base case: first column
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 选择向下走或向右走的最小值
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
