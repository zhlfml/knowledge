package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.*;

/**
 * 给你一个points数组，表示 2D 平面上的一些点，其中points[i] = [xi, yi]。
 * <p>
 * 连接点[xi, yi] 和点[xj, yj]的费用为它们之间的 曼哈顿距离：|xi - xj| + |yi - yj|，其中|val|表示val的绝对值。
 * <p>
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有一条简单路径时，才认为所有点都已连接。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1584_2.java, v 0.1 2022/6/26 13:39 xinsheng2.zhao Exp $
 */
public class Solution1584_2 {
    PriorityQueue<Edge> priorityQueue;
    boolean[]           inMST;
    List<Edge>[]        nodeEdges;

    /**
     * Prim 普里姆算法
     */
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int nodes = points.length;
        nodeEdges = new List[nodes];
        for (int i = 0; i < nodes; i++) {
            nodeEdges[i] = new ArrayList<>(nodes - 1);
        }
        for (int i = 0; i < nodes; i++) {
            for (int j = i + 1; j < nodes; j++) {
                nodeEdges[i].add(new Edge(i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
                nodeEdges[j].add(new Edge(j, i, Math.abs(points[j][0] - points[i][0]) + Math.abs(points[j][1] - points[i][1])));
            }
        }

        int answer = 0;
        inMST = new boolean[nodes];
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.distance));
        cut(0); // 可以随意一个顶点开始，这里不妨从0开始。
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            // 已在mst中的顶点可以选择别的顶点，但不能被其他顶点再次选中。
            inMST[edge.from] = true;
            // 已经处理过的顶点不能继续切
            if (!inMST[edge.to]) {
                // 选择了某条边之后，立即设置边了另一个顶点为已访问。
                // 场景：A选择了B, B选择了C，而后A又选择了C而导致成环。
                inMST[edge.to] = true;
                answer += edge.distance;
                cut(edge.to);
            }
        }

        return answer;
    }

    private void cut(int vertex) {
        for (Edge edge : nodeEdges[vertex]) {
            if (!inMST[edge.to]) {
                priorityQueue.offer(edge);
            }
        }
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

        @Override public String toString() {
            return "Edge{" + "from=" + from + ", to=" + to + ", distance=" + distance + '}';
        }
    }
}
