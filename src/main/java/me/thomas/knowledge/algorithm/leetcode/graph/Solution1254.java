package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * 二维矩阵 grid由 0（土地）和 1（水）组成。岛是由最大的4个方向连通的 0组成的群，封闭岛是一个完全 由1包围（左、上、右、下）的岛。
 * <p>
 * 请返回 封闭岛屿 的数目。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1254.java, v 0.1 2022/7/5 22:51 xinsheng2.zhao Exp $
 */
public class Solution1254 {

    public static void main(String[] args) {
        Solution1254 solution = new Solution1254();
        System.out.println(solution.closedIsland(
                new int[][] { { 0, 0, 1, 1, 0, 1, 0, 0, 1, 0 }, { 1, 1, 0, 1, 1, 0, 1, 1, 1, 0 }, { 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 }, { 0, 1, 1, 0, 0, 0, 0, 1, 0, 1 },
                              { 0, 0, 0, 0, 0, 0, 1, 1, 1, 0 }, { 0, 1, 0, 1, 0, 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1, 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
                              { 1, 1, 1, 0, 0, 1, 0, 1, 0, 1 }, { 1, 1, 1, 0, 1, 1, 0, 1, 1, 0 } }));
    }

    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int unionCount = 0; /* 合并的次数 */
        Island dummy = new Island(-1, -1);
        int row = grid.length, col = grid[0].length;
        UnionFind unionFind = new UnionFind();
        Map<Integer, Island> islandMap = new HashMap<>((int) (row * col / .75) + 1);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    Island island = new Island(i, j);
                    // 检测是否在四周边界上，如果是则与dummy连通
                    if ((i == 0 || i == row - 1) || (j == 0 || j == col - 1)) {
                        if (unionFind.union(dummy, island)) {
                            unionCount++;
                        }
                    }
                    // 检测上方和左侧是否可以合并
                    if (i > 0 && grid[i - 1][j] == 0) {
                        if (unionFind.union(islandMap.get(offset(i - 1, j, col)), island)) {
                            unionCount++;
                        }
                    }
                    if (j > 0 && grid[i][j - 1] == 0) {
                        if (unionFind.union(islandMap.get(offset(i, j - 1, col)), island)) {
                            unionCount++;
                        }
                    }
                    islandMap.put(offset(i, j, col), island);
                }
            }
        }
        /*
         * 加上dummy一共有islandMap.size() + 1个节点，一共连通了unionCount次。所以剩余的连通分量有islandMap.size() + 1 - unionCount个。
         * 下面分情况讨论dummy：
         * 如果dummy没有和任何节点连通，那么需要排除虚拟的dummy节点再减去1。
         * 如果dummy至少与一个节点连通，那么此连通分量由于比封闭，所以得除去还是需要再减去1。
         * 综上所述，剩余的封闭的连通分量有 islandMap.size() + 1 - unionCount - 1 => islandMap.size() - unionCount。
         */
        return islandMap.size() - unionCount;
    }

    private int offset(int i, int j, int col) {
        return i * col + j;
    }

    private static class Island {
        int    row;
        int    col;
        Island parent;
        int    size;

        public Island(int row, int col) {
            this.row = row;
            this.col = col;
            this.parent = this;
            this.size = 1;
        }
    }

    private static class UnionFind {
        public UnionFind() {
        }

        public boolean union(Island x, Island y) {
            Island rootX = root(x);
            Island rootY = root(y);
            // 计算连通分量时一定要关注之前是否已连通
            if (rootX == rootY) {
                return false;
            }
            if (rootX.size < rootY.size) {
                rootX.parent = rootY;
                rootY.size += rootX.size;
            } else {
                rootY.parent = rootX;
                rootX.size += rootY.size;
            }
            return true;
        }

        public boolean connected(Island x, Island y) {
            Island rootX = root(x);
            Island rootY = root(y);
            return rootX == rootY;
        }

        public Island root(Island island) {
            if (island.parent == island) {
                return island;
            }
            return island.parent = root(island.parent);
        }
    }
}
