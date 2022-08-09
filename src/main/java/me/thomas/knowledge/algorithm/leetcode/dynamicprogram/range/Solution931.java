package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.range;

/**
 * 931. 下降路径最小和
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * <p>
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution931.java, v 0.1 2022/8/7 13:42 xinsheng2.zhao Exp $
 */
public class Solution931 {

    public int minFallingPathSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        return minFallingPathSum_dp1(matrix);
    }

    /**
     * 定义dp为一维数组
     */
    int minFallingPathSum_dp1(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int[] dp = null, prev = null; /* dp含义：从第一行任意列下降到matrix[i][j]的最小和 */
        for (int i = 0; i < row; i++) {
            dp = new int[col]; /* create dp matrix each row, because at last prev = dp */
            for (int j = 0; j < col; j++) {
                // base case
                if (i == 0) {
                    dp[j] = matrix[i][j];
                } else {
                    if (j == 0) {
                        dp[j] = Math.min(prev[j], prev[j + 1]) + matrix[i][j];
                    } else if (j < col - 1) {
                        dp[j] = min(prev[j - 1], prev[j], prev[j + 1]) + matrix[i][j];
                    } else {
                        dp[j] = Math.min(prev[j - 1], prev[j]) + matrix[i][j];
                    }
                }
            }
            prev = dp;
        }
        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < col; j++) {
            answer = Math.min(answer, dp[j]);
        }
        return answer;
    }

    /**
     * 定义dp为二维数组
     */
    int minFallingPathSum_dp2(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row][col]; /* dp含义：从第一行任意列下降到matrix[i][j]的最小和 */
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // base case
                if (i == 0) {
                    dp[0][j] = matrix[i][j];
                } else {
                    if (j == 0) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j];
                    } else if (j < col - 1) {
                        dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + matrix[i][j];
                    }
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < col; j++) {
            answer = Math.min(answer, dp[row - 1][j]);
        }
        return answer;
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
