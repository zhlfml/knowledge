package me.thomas.knowledge.algorithm.leetcode.matrix;

/**
 * 48. 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution48.java, v 0.1 2022/10/14 16:53 xinsheng2.zhao Exp $
 */
public class Solution48 {

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int n = matrix.length;
        // 按照捺对角线`(0, 0) - (n - 1, n - 1)`翻转，只需对调(i, j)即可
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }

        // 每行左右交换, 图像顺时针旋转90度
        for (int i = 0; i < n; i++) {
            int j = 0, k = n - 1;
            while (j < k) {
                swap(matrix, i, j, i, k);
                j++;
                k--;
            }
        }

        // 若每列上下交换就是逆时针旋转90度
        /*for (int j = 0; j < n; j++) {
            int i = 0, k = n - 1;
            while (i < k) {
                swap(matrix, i, j, k, j);
                i++;
                k--;
            }
        }*/
    }

    void swap(int[][] matrix, int i, int j, int mi, int mj) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[mi][mj];
        matrix[mi][mj] = tmp;
    }
}
