package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.*;

/**
 * 有 n 个城市通过一些航班连接。给你一个数组flights ，其中flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
 * <p>
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution787.java, v 0.1 2022/6/25 13:50 xinsheng2.zhao Exp $
 */
public class Solution787 {

    final int INF = 0x3fffffff;

    /**
     * 输入:
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 1
     * 输出: 200
     * <p>
     * 引入了边和点的模型。
     * 思路：广度优先遍历，若中转次数超过了k，则放弃，若达到终点则取价格最便宜的路线。
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 构建图：邻接表
        List<Edge>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] flight : flights) {
            graph[flight[0]].add(new Edge(flight[1], flight[2]));
        }
        return dijkstra(graph, src, dst, k);
    }

    /**
     * 传统广度优先遍历算法，通过普通队列按照层次遍历。
     * 是否将下一层的节点纳入考量范围，唯一的因素是价格需要更低。
     */
    int bfs(List<Edge>[] graph, int src, int dst, int k) {
        int answer = INF;
        int[] costs = new int[graph.length]; /* 关键：从起点到达城市i的最低费用为prices[i]，这里不能简单的通过是否访问过作为判断逻辑，因为中转次数多的费用未必更多 */
        Arrays.fill(costs, INF); /* 默认到达每个城市的费用为无穷大 */
        Queue<Vertex> queue = new LinkedList<>();
        costs[src] = 0; // base case: 始发站城市费用为0
        queue.offer(new Vertex(src, 0, 0));
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            // 是否到达了终点
            if (vertex.id == dst) {
                answer = Math.min(answer, vertex.price);
                continue;
            }
            if (vertex.times - 1 == k) { // 飞行的次数减去1就是中转的次数，如果中转次数已到达k次，则不可以继续飞行。
                continue;
            }
            for (Edge edge : graph[vertex.id]) {
                int price = edge.price + vertex.price; // 到达下一站的费用
                if (costs[edge.vertex] <= price) { // 如果之前某条路径同样到达相同的下一站城市且费用更低则无需做无用功，可以立即跳过。
                    continue;
                }
                costs[edge.vertex] = price;
                queue.offer(new Vertex(edge.vertex, price, vertex.times + 1));
            }
        }
        return answer == INF ? -1 : answer;
    }

    /**
     * dijkstra算法，通过优先权队列按照花费排序遍历。
     * 是否将下一层的节点纳入考量范围，取决于价格是否更低或中转次数是否更少。
     */
    int dijkstra(List<Edge>[] graph, int src, int dst, int k) {
        int[] transfers = new int[graph.length]; /* 从起点到达城市i的最少中转次数为transfers[i] */
        int[] costs = new int[graph.length]; /* 从起点到达城市i的最低费用为prices[i] */
        Arrays.fill(transfers, INF); /* 默认到达每个城市的中转次数为无穷大 */
        Arrays.fill(costs, INF); /* 默认到达每个城市的费用为无穷大 */

        Queue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.price));
        // base case
        transfers[src] = 0;
        costs[src] = 0;
        queue.offer(new Vertex(src, 0, 0));
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            if (vertex.id == dst) {
                return vertex.price;
            }
            // 中转次数超过限制
            if (vertex.times - 1 == k) {
                continue;
            }
            for (Edge edge : graph[vertex.id]) {
                int cost = vertex.price + edge.price;
                int transfer = vertex.times + 1;
                // 加入的唯一条件是价格更低或中转次数更少
                if (costs[edge.vertex] <= cost && transfers[edge.vertex] <= transfer) {
                    continue;
                }
                costs[edge.vertex] = cost;
                transfers[edge.vertex] = transfer;
                queue.offer(new Vertex(edge.vertex, cost, transfer));
            }
        }
        return -1;
    }

    static class Edge {
        int vertex; /* 边另一头的点 */
        int price; /* 飞行线路价格 */

        public Edge(int vertex, int price) {
            this.vertex = vertex;
            this.price = price;
        }
    }

    static class Vertex {
        int id;
        int price; /* 从出发站点到达此站的费用 */
        int times; /* 飞行的次数 */

        public Vertex(int id, int price, int times) {
            this.id = id;
            this.price = price;
            this.times = times;
        }
    }

    public static void main(String[] args) {
        Solution787 solution = new Solution787();
        int n = 5;
        int[][] flights = new int[][] { { 0, 1, 5 }, { 1, 2, 5 }, { 0, 3, 2 }, { 3, 1, 2 }, { 1, 4, 1 }, { 4, 2, 1 } };
        int src = 0;
        int dst = 2;
        int k = 2;
        System.out.println(solution.findCheapestPrice(n, flights, src, dst, k));
    }
}
