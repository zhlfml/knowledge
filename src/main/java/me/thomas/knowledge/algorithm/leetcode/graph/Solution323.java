package me.thomas.knowledge.algorithm.leetcode.graph;

/**
 * 给你输入一个包含 n 个节点的图，用一个整数 n 和一个数组 edges 表示，其中 edges[i] = [ai, bi] 表示图中节点 ai 和 bi 之间有一条边。请你计算这幅图的连通分量个数。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution323.java, v 0.1 2022/6/24 15:24 xinsheng2.zhao Exp $
 */
public class Solution323 {

    public int countComponents(int n, int[][] edges) {
        if (n < 1 || edges == null) {
            return 0;
        }
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        return unionFind.count();
    }

    private class UnionFind {
        int[] parent;
        int   count; // 连通分量

        public UnionFind(int n) {
            this.parent = new int[n];
            this.count = n;

            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
        }

        void union(int x, int y) {
            int rootX = findRoot(x);
            int rootY = findRoot(y);
            if (rootX == rootY) { // 计算连通分量时一定要关注之前是否已连通
                return;
            }
            parent[rootX] = rootY;
            count--;
        }

        boolean connected(int x, int y) {
            return findRoot(x) == findRoot(y);
        }

        int count() {
            return count;
        }

        int findRoot(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = findRoot(parent[x]);
        }
    }

    public static void main(String[] args) {
        Solution323 solution323 = new Solution323();
        int[][] edges = new int[][] { { 0, 1 }, { 1, 0 } };
        System.out.println(solution323.countComponents(3, edges));
    }
}
