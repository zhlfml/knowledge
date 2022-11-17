package me.thomas.knowledge.algorithm.leetcode.matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 864. 获取所有钥匙的最短路径
 * 给定一个二维网格 grid ，其中：
 * <p>
 * '.' 代表一个空房间
 * '#' 代表一堵
 * '@' 是起点
 * 小写字母代表钥匙
 * 大写字母代表锁
 * 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。
 * <p>
 * 假设 k 为 钥匙/锁 的个数，且满足 1 <= k <= 6，字母表中的前 k 个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
 * <p>
 * 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 -1 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution864.java, v 0.1 2022/11/10 22:26 xinsheng2.zhao Exp $
 */
public class Solution864 {

    public int shortestPathAllKeys(String[] grid) {
        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        // 寻找入口以及钥匙的数量
        int m = grid.length, n = grid[0].length();
        int si = 0, sj = 0; /* 入口位置 */
        int keys = 0; /* 钥匙数量 */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    si = i;
                    sj = j;
                }
                if (c >= 'a' && c <= 'f') {
                    keys = Math.max(keys, c - 'a' + 1);
                }
            }
        }

        // 初始化数组
        int[][][] dists = new int[m][n][1 << keys];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dists[i][j], -1); // 初始值设置为-1，表示尚未走过。
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { si, sj, 0 }); // 0表示一把钥匙没有找到
        dists[si][sj][0] = 0; // 三维数组的值为记录走的步数
        while (!queue.isEmpty()) {
            int[] point = queue.poll(); // 当前所在位置
            int i = point[0], j = point[1], mask = point[2];
            /*
             * 朝四个方向探索，钥匙没有变化时不能走回头路。
             */
            for (int k = 0; k < 4; k++) {
                int ni = i + dirs[k][0];
                int nj = j + dirs[k][1];
                if (ni < 0 || ni >= m || nj < 0 || nj >= n) {
                    continue;
                }
                if (dists[ni][nj][mask] > -1) { // 钥匙未变的情况下每个房间只能访问一次，防止无止境的来回走动。
                    continue;
                }
                char c = grid[ni].charAt(nj);
                if (c == '#') {
                    continue;
                }
                if (c == '.' || c == '@' || (c >= 'A' && c <= 'F' && (mask & (1 << c - 'A')) > 0)) { /* 进入无锁房间或通过钥匙进入带锁的房间 */
                    dists[ni][nj][mask] = dists[i][j][mask] + 1;
                    queue.offer(new int[] { ni, nj, mask });
                    continue;
                }
                if (c >= 'a' && c <= 'f') { // 找到钥匙
                    int nmask = mask | (1 << c - 'a');
                    dists[ni][nj][nmask] = dists[i][j][mask] + 1; /* 注意这里的变化 -- nmask */
                    if (nmask == (1 << keys) - 1) { // 找到了所有的钥匙
                        return dists[ni][nj][nmask];
                    }
                    queue.offer(new int[] { ni, nj, nmask });
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution864 solution864 = new Solution864();
        System.out.println(solution864.shortestPathAllKeys(new String[] { "@.a..", "###.#", "b.A.B" }));
    }

}
