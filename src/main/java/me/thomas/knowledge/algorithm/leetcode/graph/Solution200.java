package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.Arrays;

/**
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution200.java, v 0.1 2022/7/5 20:32 xinsheng2.zhao Exp $
 */
public class Solution200 {

    public static void main(String[] args) {
        Solution200 solution = new Solution200();
        System.out.println(
                solution.numIslands(new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '0', '0', '0' } }));
        System.out.println(
                solution.numIslands(new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '1', '0', '0' }, { '0', '0', '0', '1', '1' } }));
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length, col = grid[0].length;
        int islandsCount = 0;
        UnionFind unionFind = new UnionFind(row * col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                islandsCount++;
                if (j + 1 < col && grid[i][j + 1] == '1') {
                    unionFind.union(i * col + j, i * col + (j + 1));
                }
                if (i + 1 < row && grid[i + 1][j] == '1') {
                    unionFind.union(i * col + j, (i + 1) * col + j);
                }
            }
        }
        // 岛屿合并后的数量 = 合并前岛屿的数量 - 岛屿合并的次数（节点总数 - 连通数量）
        return islandsCount - (row * col - unionFind.count);
    }

    private static class UnionFind {
        /**
         * 父节点
         */
        private final int[] parent;
        /**
         * 子树包含的节点数
         */
        private final int[] size;
        /**
         * 子树的数量
         */
        private       int   count;

        /**
         * @param count 节点的数量
         */
        public UnionFind(int count) {
            this.count = count;
            this.parent = new int[count];
            for (int i = 0; i < count; i++) {
                this.parent[i] = i;
            }
            this.size = new int[count];
            Arrays.fill(this.size, 1);
        }

        public void union(int x, int y) {
            int rootX = root(x);
            int rootY = root(y);
            // 计算连通分量时一定要关注之前是否已连通
            if (rootX == rootY) {
                return;
            }
            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
            count--;
        }

        public boolean connected(int x, int y) {
            int rootX = root(x);
            int rootY = root(y);
            return rootX == rootY;
        }

        public int count() {
            return count;
        }

        private int root(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = root(parent[x]);
        }
    }
}
