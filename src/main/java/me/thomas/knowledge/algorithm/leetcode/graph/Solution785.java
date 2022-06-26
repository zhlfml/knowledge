package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 存在一个 无向图 ，图中有 n 个节点。其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号。给你一个二维数组 graph ，其中 graph[u] 是一个节点数组，由节点 u 的邻接节点组成。形式上，对于graph[u] 中的每个 v ，都存在一条位于节点 u 和节点 v 之间的无向边。该无向图同时具有以下属性：
 * 不存在自环（graph[u] 不包含 u）。
 * 不存在平行边（graph[u] 不包含重复值）。
 * 如果 v 在 graph[u] 内，那么 u 也应该在 graph[v] 内（该图是无向图）
 * 这个图可能不是连通图，也就是说两个节点 u 和 v 之间可能不存在一条连通彼此的路径。
 * 二分图 定义：如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称为 二分图 。
 * <p>
 * 如果图是二分图，返回 true ；否则，返回 false 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution785.java, v 0.1 2022/6/26 08:43 xinsheng2.zhao Exp $
 */
public class Solution785 {

    /**
     * true: 二分图 false: 非二分图
     */
    boolean   pass = true;
    /**
     * false和true代表不同的颜色
     */
    boolean[] nodeColors;

    boolean[] visited;

    public boolean isBipartite(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return false;
        }
        int nodes = graph.length;
        nodeColors = new boolean[nodes];
        visited = new boolean[nodes];

        // 在外围展开循环
        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                bfs(graph, i);
            }
        }

        return pass;
    }

    /**
     * 图的遍历框架: DFS
     */
    private void dfs(int[][] graph, int start) {
        if (!pass) {
            return;
        }
        visited[start] = true;
        for (int neighbor : graph[start]) {
            if (!visited[neighbor]) {
                nodeColors[neighbor] = !nodeColors[start];
                dfs(graph, neighbor);
            } else {
                if (nodeColors[neighbor] == nodeColors[start]) {
                    pass = false;
                    return;
                }
            }
        }
    }

    /**
     * 图的遍历框架: BFS
     */
    private void bfs(int[][] graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            visited[vertex] = true;
            for (int neighbor : graph[vertex]) {
                if (!visited[neighbor]) {
                    nodeColors[neighbor] = !nodeColors[vertex];
                    queue.offer(neighbor);
                } else {
                    if (nodeColors[neighbor] == nodeColors[vertex]) {
                        pass = false;
                        return;
                    }
                }
            }
        }
    }
}
