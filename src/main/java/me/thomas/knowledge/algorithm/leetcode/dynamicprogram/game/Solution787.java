package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    int[][] memo;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // graph: 到达toi的线路有哪些fromi
        List<Edge>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] flight : flights) {
            graph[flight[1]].add(new Edge(flight[0], flight[2]));
        }
        k++; /* 关键：将中转站数量转化为边的数量 */
        // 自顶向下dp
        memo = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], INF);
        }
        int answer = dp(graph, src, dst, k);
        return answer < INF ? answer : -1;
    }

    /**
     * 含义：从起点到达dst，在最大k次中转线路的最少费用为dp[dst][k]
     */
    int dp(List<Edge>[] graph, int src, int dst, int k) {
        if (memo[dst][k] < INF) {
            return memo[dst][k];
        }
        // base case 1
        if (src == dst) {
            return 0;
        }
        // base case 2
        if (k == 0) {
            return INF;
        }
        int answer = INF;
        for (Edge edge : graph[dst]) {
            answer = Math.min(answer, dp(graph, src, edge.src, k - 1) + edge.price);
        }
        memo[dst][k] = answer;
        return memo[dst][k];
    }

    /**
     * 线
     */
    static class Edge {
        /**
         * 边的另一端城市代号
         */
        int src;
        /**
         * 到另一端城市航班的价格
         */
        int price;

        public Edge(int src, int price) {
            this.src = src;
            this.price = price;
        }
    }

    /**
     * 点
     */
    static class Vertex {
        /**
         * 当前城市代号
         */
        int src;
        /**
         * 到当前城市航班花费
         */
        int cost;
        /**
         * 到当前城市航班中转次数
         */
        int transfer;

        public Vertex(int src, int cost, int transfer) {
            this.src = src;
            this.cost = cost;
            this.transfer = transfer;
        }
    }

    public static void main(String[] args) {
        Solution787 solution = new Solution787();
        int n = 5;
        int[][] flights = new int[][] { { 0, 1, 5 }, { 1, 2, 5 }, { 0, 3, 2 }, { 3, 1, 2 }, { 1, 4, 1 }, { 4, 2, 1 } };
        int src = 0;
        int dst = 1;
        int k = 1;
        System.out.println(solution.findCheapestPrice(n, flights, src, dst, k));
    }
}
