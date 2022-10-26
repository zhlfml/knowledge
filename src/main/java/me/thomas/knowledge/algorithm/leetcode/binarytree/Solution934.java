package me.thomas.knowledge.algorithm.leetcode.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 934. 最短的桥
 * 给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
 * <p>
 * 岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
 * <p>
 * 你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
 * <p>
 * 返回必须翻转的 0 的最小数目。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution934.java, v 0.1 2022/10/26 23:08 xinsheng2.zhao Exp $
 */
public class Solution934 {

    /**
     * 先深度优先搜索，将其中一个岛改成其他标识，比方说1改成2并收集起来，再广度优先搜索，碰到1的最短距离就是需要翻转0的数目。
     */
    public int shortestBridge(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length, n = grid[0].length;
        boolean found = false;
        int i = 0, j = 0;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        //System.out.printf("i = %d, j = %d\n", i, j);
        dfs(queue, grid, i, j);
        //for (int k = 0; k < grid.length; k++) {
        //    System.out.println(Arrays.toString(grid[k]));
        //}
        return bfs(queue, grid);
    }

    void dfs(Queue<int[]> queue, int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        if (grid[i][j] == 1) {
            queue.offer(new int[] { i, j });
            grid[i][j] = 2;
            dfs(queue, grid, i - 1, j); // 上
            dfs(queue, grid, i + 1, j); // 下
            dfs(queue, grid, i, j - 1); // 左
            dfs(queue, grid, i, j + 1); // 右
        }
    }

    int bfs(Queue<int[]> queue, int[][] grid) {
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                if (point[0] - 1 >= 0 && judge(queue, grid, point[0] - 1, point[1])) {
                    return distance;
                }
                if (point[0] + 1 < grid.length && judge(queue, grid, point[0] + 1, point[1])) {
                    return distance;
                }
                if (point[1] - 1 >= 0 && judge(queue, grid, point[0], point[1] - 1)) {
                    return distance;
                }
                if (point[1] + 1 < grid[0].length && judge(queue, grid, point[0], point[1] + 1)) {
                    return distance;
                }
            }
            distance++;
        }
        return -1;
    }

    boolean judge(Queue<int[]> queue, int[][] grid, int i, int j) {
        int val = grid[i][j];
        if (val == 1) {
            return true;
        }
        if (val == 0) {
            grid[i][j] = -1; // 防止被放入队列两次
            queue.offer(new int[] { i, j });
        }
        return false;
    }
}
