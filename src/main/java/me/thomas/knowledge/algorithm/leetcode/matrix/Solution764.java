package me.thomas.knowledge.algorithm.leetcode.matrix;

/**
 * 764. 最大加号标志
 * 在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示 grid[xi][yi] == 0
 * <p>
 * 返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
 * <p>
 * 一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution764.java, v 0.1 2022/11/9 23:26 xinsheng2.zhao Exp $
 */
public class Solution764 {

    /**
     * 思路：贪心法，从矩阵中心位置顺时针向外扩散寻找最大轴对齐的阶数。在任意点可以根据点到最近的一条边的距离可以得到理论最大阶数。
     * 只要之前出现过的阶数大于了理论最大阶数，那么就没有必要继续查找了。
     */
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        if (mines.length == n * n) {
            return 0;
        }
        // 初始化grid矩阵
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 1;
            }
        }
        for (int[] mine : mines) {
            grid[mine[0]][mine[1]] = 0;
        }

        // 旋转时不能走回头路
        boolean[][] visited = new boolean[n][n];
        // 从中心点顺时针旋转
        int i = (n - 1) / 2, j = (n - 1) / 2;
        Direction dir = Direction.STAY;
        int maxDegree = 1;
        int visitCount = 0, totalCount = n * n;
        while (visitCount < totalCount) {
            if (maxDegree >= maxDegreeOnTheory(n, i, j)) {
                break;
            }
            switch (dir) {
                case RIGHT:
                    j++;
                    if (j >= n || visited[i][j]) {
                        j--;
                        dir = Direction.UP;
                    } else {
                        maxDegree = Math.max(maxDegree, degree(grid, i, j));
                        visitCount++;
                        visited[i][j] = true;
                        dir = Direction.DOWN;
                    }
                    break;
                case DOWN:
                    i++;
                    if (i >= n || visited[i][j]) {
                        i--;
                        dir = Direction.RIGHT;
                    } else {
                        maxDegree = Math.max(maxDegree, degree(grid, i, j));
                        visitCount++;
                        visited[i][j] = true;
                        dir = Direction.LEFT;
                    }
                    break;
                case LEFT:
                    j--;
                    if (j < 0 || visited[i][j]) {
                        j++;
                        dir = Direction.DOWN;
                    } else {
                        maxDegree = Math.max(maxDegree, degree(grid, i, j));
                        visitCount++;
                        visited[i][j] = true;
                        dir = Direction.UP;
                    }
                    break;
                case UP:
                    i--;
                    if (i < 0 || visited[i][j]) {
                        i++;
                        dir = Direction.LEFT;
                    } else {
                        maxDegree = Math.max(maxDegree, degree(grid, i, j));
                        visitCount++;
                        visited[i][j] = true;
                        dir = Direction.RIGHT;
                    }
                    break;
                default:
                    maxDegree = Math.max(maxDegree, degree(grid, i, j));
                    visitCount++;
                    visited[i][j] = true;
                    dir = Direction.RIGHT;
                    break;
            }
        }
        return maxDegree;
    }

    enum Direction {
        RIGHT, DOWN, LEFT, UP, STAY
    }

    // 计算grid[i][j]的阶数
    int degree(int[][] grid, int i, int j) {
        if (grid[i][j] == 0) {
            return 0;
        }
        int degree = (grid.length + 1) / 2; // 最大阶数
        // 四个方向分别探测阶数
        // 左
        int k;
        for (k = j; k >= 0; k--) {
            if (grid[i][k] == 0) {
                degree = Math.min(degree, j - k);
                break;
            }
            if (k == 0) {
                degree = Math.min(degree, j - k + 1);
            }
        }
        // 右
        for (k = j; k < grid.length; k++) {
            if (grid[i][k] == 0) {
                degree = Math.min(degree, k - j);
                break;
            }
            if (k == grid.length - 1) {
                degree = Math.min(degree, k - j + 1);
            }
        }
        // 上
        for (k = i; k >= 0; k--) {
            if (grid[k][j] == 0) {
                degree = Math.min(degree, i - k);
                break;
            }
            if (k == 0) {
                degree = Math.min(degree, i - k + 1);
            }
        }
        // 下
        for (k = i; k < grid.length; k++) {
            if (grid[k][j] == 0) {
                degree = Math.min(degree, k - i);
                break;
            }
            if (k == grid.length - 1) {
                degree = Math.min(degree, k - i + 1);
            }
        }
        return degree;
    }

    int maxDegreeOnTheory(int n, int i, int j) {
        return Math.min(Math.min(n - i, i + 1), Math.min(n - j, j + 1));
    }

    public static void main(String[] args) {
        Solution764 solution764 = new Solution764();
        System.out.println(solution764.orderOfLargestPlusSign(2, new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 } })); // 1
        System.out.println(solution764.orderOfLargestPlusSign(3, new int[][] { { 0, 1 } })); // 1
    }
}
