package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 你准备参加一场远足活动。给你一个二维rows x columns的地图heights，其中heights[row][col]表示格子(row, col)的高度。一开始你在最左上角的格子(0, 0)，且你希望去最右下角的格子(rows-1, columns-1)（注意下标从 0 开始编号）。你每次可以往 上，下，左，右四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * <p>
 * 一条路径耗费的 体力值是路径上相邻格子之间 高度差绝对值的 最大值决定的。
 * <p>
 * 请你返回从左上角走到右下角的最小体力消耗值。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1631.java, v 0.1 2022/6/25 11:06 xinsheng2.zhao Exp $
 */
public class Solution1631 {

    public int minimumEffortPath(int[][] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int row = heights.length, col = heights[0].length;
        // 顺时针四个方向
        int[][] dirs = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        // 构建图
        Vertex[] vertices = new Vertex[row * col];
        List<Vertex>[] graph = new List[row * col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                vertices[i * col + j] = new Vertex(i, j, heights[i][j]);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                List<Vertex> aroundVertices = new ArrayList<>(4); // 每个点最多只能与周围的4个点相连
                for (int[] dir : dirs) {
                    int ii = i + dir[0], jj = j + dir[1];
                    if ((ii >= 0 && ii < row) && (jj >= 0 && jj < col)) {
                        aroundVertices.add(vertices[ii * col + jj]);
                    }
                }
                graph[i * col + j] = aroundVertices;
            }
        }

        boolean[] visited = new boolean[row * col];
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(v -> v.diff));
        priorityQueue.offer(vertices[0]);
        while (!priorityQueue.isEmpty()) {
            Vertex vertex = priorityQueue.poll();
            int offset = vertex.row * col + vertex.col;
            if (visited[offset]) {
                continue;
            }
            visited[offset] = true;
            // 到达终点，返回结果
            if (offset == row * col - 1) {
                return vertex.diff;
            }
            for (Vertex arround : graph[offset]) {
                // 放入Vertex的副本
                priorityQueue.offer(new Vertex(arround, Math.max(vertex.diff, Math.abs(arround.height - vertex.height))));
            }
        }
        return 0;
    }

    private class Vertex {
        int row;
        int col;
        int height;
        // 与相邻节点的高度差
        int diff;

        public Vertex(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
            this.diff = 0;
        }

        public Vertex(Vertex v, int diff) {
            this.row = v.row;
            this.col = v.col;
            this.height = v.height;
            this.diff = diff;
        }
    }
}
