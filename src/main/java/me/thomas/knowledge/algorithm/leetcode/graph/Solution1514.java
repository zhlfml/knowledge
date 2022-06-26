package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 edges[i] = [a, b] 表示连接节点 a 和 b 的一条无向边，且该边遍历成功的概率为 succProb[i] 。
 * <p>
 * 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。
 * <p>
 * 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1514.java, v 0.1 2022/6/25 10:54 xinsheng2.zhao Exp $
 */
public class Solution1514 {

    /**
     * 思路：按照大概率的路径查找，第一个到达终点站的路径就是概率最大的路径。
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        // 构建图
        List<Vertex>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            // 无向图，两点之间都得加出度的线
            graph[edges[i][0]].add(new Vertex(edges[i][1], succProb[i]));
            graph[edges[i][1]].add(new Vertex(edges[i][0], succProb[i]));
        }

        boolean[] visited = new boolean[n];
        PriorityQueue<Vertex> queue = new PriorityQueue<>((e1, e2) -> e1.succProb <= e2.succProb ? 1 : -1); // 按照概率降序排列
        queue.offer(new Vertex(start, 1.0));
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            if (visited[vertex.vertex]) {
                continue;
            }
            visited[vertex.vertex] = true;
            // 如果顶点是终点则退出
            if (vertex.vertex == end) {
                return vertex.succProb;
            }
            for (Vertex next : graph[vertex.vertex]) {
                queue.offer(new Vertex(next.vertex, vertex.succProb * next.succProb));
            }
        }

        return 0;
    }

    private class Vertex {
        int    vertex; /* 顶点的代号 */
        double succProb; /* 从出发点到达此点的成功概率 */

        public Vertex(int vertex, double succProb) {
            this.vertex = vertex;
            this.succProb = succProb;
        }
    }
}
