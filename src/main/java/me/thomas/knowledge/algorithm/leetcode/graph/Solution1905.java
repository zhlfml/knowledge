package me.thomas.knowledge.algorithm.leetcode.graph;

/**
 * 给你两个m x n的二进制矩阵grid1 和grid2，它们只包含0（表示水域）和 1（表示陆地）。一个 岛屿是由 四个方向（水平或者竖直）上相邻的1组成的区域。任何矩阵以外的区域都视为水域。
 * <p>
 * 如果 grid2的一个岛屿，被 grid1的一个岛屿完全 包含，也就是说 grid2中该岛屿的每一个格子都被 grid1中同一个岛屿完全包含，那么我们称 grid2中的这个岛屿为 子岛屿。
 * <p>
 * 请你返回 grid2中 子岛屿的 数目。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1905.java, v 0.1 2022/7/7 21:39 xinsheng2.zhao Exp $
 */
public class Solution1905 {

    int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    /**
     * 思路：grid2的每块陆地检查grid1的对应位置，如果grid1也是陆地，将grid2的陆地变成2.
     * 找出grid2中相邻的陆地都是2的岛屿的数量（一旦与陆地1相邻则不计在内）。
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        if (grid1 == null || grid1.length == 0 || grid2 == null || grid2.length == 0) {
            return 0;
        }
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[i].length; j++) {
                if (grid2[i][j] == 1 && grid1[i][j] == 1) {
                    grid2[i][j] = 2;
                }
            }
        }
        // 找出大于0的陆地
        int answer = 0;
        for (int i = 0; i < grid2.length; i++) {
            for (int j = 0; j < grid2[i].length; j++) {
                if (grid2[i][j] == 2 && dfs(grid2, i, j)) {
                    // System.out.printf("i=%d, j=%d\n", i, j);
                    answer++;
                }
            }
        }
        return answer;
    }

    /**
     * @param grid 区域
     * @param x    所在行
     * @param y    所在列
     * @return 岛屿是否全是2组成，true是 false否
     */
    boolean dfs(int[][] grid, int x, int y) {
        boolean answer = grid[x][y] == 2;
        grid[x][y] = 0;
        for (int[] dir : dirs) {
            int xx = x + dir[0], yy = y + dir[1];
            if ((0 <= xx && xx < grid.length) && (0 <= yy && yy < grid[xx].length)) {
                if (grid[xx][yy] > 0 && !dfs(grid, xx, yy)) {
                    answer = false;
                }
            }
        }
        return answer;
    }

}
