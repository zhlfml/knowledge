package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 给你一个points数组，表示 2D 平面上的一些点，其中points[i] = [xi, yi]。
 * <p>
 * 连接点[xi, yi] 和点[xj, yj]的费用为它们之间的 曼哈顿距离：|xi - xj| + |yi - yj|，其中|val|表示val的绝对值。
 * <p>
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有一条简单路径时，才认为所有点都已连接。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1584.java, v 0.1 2022/6/26 11:35 xinsheng2.zhao Exp $
 */
public class Solution1584 {

    /**
     * Kruskal 克鲁斯卡尔算法
     */
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int nodes = points.length;
        List<Edge> edges = new ArrayList<>(nodes * (nodes - 1) / 2);
        for (int i = 0; i < nodes; i++) {
            for (int j = i + 1; j < nodes; j++) {
                edges.add(new Edge(i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }
        edges.sort(Comparator.comparingInt(e -> e.distance));
        int answer = 0;
        UnionFind unionFind = new UnionFind(nodes);
        for (Edge edge : edges) {
            if (unionFind.connected(edge.from, edge.to)) {
                continue;
            }
            unionFind.union(edge.from, edge.to);
            answer += edge.distance;
        }
        // 检查是否所有的节点都使用上了
        return unionFind.size[unionFind.root(0)] == nodes ? answer : -1;
    }

    private class Edge {
        int from;
        int to;
        int distance;

        public Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }

    private class UnionFind {
        /**
         * 连通分量
         */
        int   count;
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            count = n;
            size = new int[n];
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        void union(int x, int y) {
            int rootX = root(x);
            int rootY = root(y);
            if (rootX == rootY) {
                return;
            }
            if (size[x] < size[y]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
            count--;
        }

        boolean connected(int x, int y) {
            return root(x) == root(y);
        }

        int root(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = root(parent[x]);
        }
    }

}
