package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * <p>
 * 岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * <p>
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution695.java, v 0.1 2022/7/7 20:45 xinsheng2.zhao Exp $
 */
public class Solution695 {

    int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    /**
     * 深度优先搜索所有相邻的"土地"，返回相邻"土地"的面积。
     *
     * @param grid 岛屿
     * @param x    "土地"所在的行
     * @param y    "土地"所在的列
     * @return 位置相邻"土地"的面积
     */
    int dfs(int[][] grid, int x, int y) {
        grid[x][y] = 0;
        int area = 1;
        for (int[] dir : dirs) {
            int xx = x + dir[0], yy = y + dir[1];
            if ((0 <= xx && xx < grid.length) && (0 <= yy && yy < grid[xx].length)) {
                if (grid[xx][yy] == 1) {
                    area += dfs(grid, xx, yy);
                }
            }
        }
        return area;
    }

    /**
     * 广度优先搜索所有相邻的"土地"，返回相邻"土地"的面积。
     *
     * @param grid 岛屿
     * @param x    "土地"所在的行
     * @param y    "土地"所在的列
     * @return 位置相邻"土地"的面积
     */
    int bfs(int[][] grid, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { x, y });

        int area = 0;
        while (!queue.isEmpty()) {
            area++;
            int[] pos = queue.poll();
            for (int[] dir : dirs) {
                int xx = pos[0] + dir[0], yy = pos[1] + dir[1];
                if ((0 <= xx && xx < grid.length) && (0 <= yy && yy < grid[xx].length)) {
                    if (grid[xx][yy] == 1) {
                        grid[xx][yy] = 0; // 为防止重复放入队列，再入队列之前将状态改成0。
                        queue.offer(new int[] { xx, yy });
                    }
                }
            }
        }
        return area;
    }
}
