package me.thomas.knowledge.algorithm.leetcode.graph;

import java.util.Random;

/**
 * 给你 n 个人的社交关系（你知道任意两个人之间是否认识），然后请你找出这些人中的「名人」。
 * 所谓「名人」有两个条件：
 * 1、所有其他人都认识「名人」。
 * 2、「名人」不认识任何其他人。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution227.java, v 0.1 2022/6/24 10:26 xinsheng2.zhao Exp $
 */
public class Solution227 {

    /**
     * 社交圈人数
     */
    private int n;

    /**
     * 给你输入一个大小为 n x n 的二维数组（邻接矩阵） graph 表示一幅有 n 个节点的图，每个人都是图中的一个节点，编号为 0 到 n - 1。
     * 如果 graph[i][j] == 1 代表第 i 个人认识第 j 个人，如果 graph[i][j] == 0 代表第 i 个人不认识第 j 个人。
     */
    private int[][] graph; // 邻接矩阵

    /**
     * 查找名人
     *
     * @param n 社交圈共n个人，编号从 0 到 n - 1
     * @return 名人的编号
     */
    public int findCelebrity(int n) {
        int i = 0, j = n - 1;
        while (i < j) {
            if (knows(i, j)) { // i认识j，i不是名人
                i++;
            } else if (knows(j, i)) { // j认识i，j不是名人
                j--;
            } else { // i,j互补认识，都不是名人
                i++;
                j--;
            }
        }
        // 剩下的名人就是i和j指向的编码（i == j），验证此人是否真名人
        boolean celebrity = true;
        for (int k = 0; k < n; k++) {
            if (i == k) {
                continue;
            }
            if (knows(i, k) && !knows(k, j)) {
                celebrity = false;
                break;
            }
        }
        return celebrity ? i : -1;
    }

    // 可以直接调用，能够返回 i 是否认识 j
    private boolean knows(int i, int j) {
        return graph[i][j] == 1;
    }

    public static void main(String[] args) {
        Solution227 solution = new Solution227();
        solution.n = 40;
        solution.graph = new int[solution.n][solution.n];
        Random random = new Random();
        // 抽取名人编号
        int celebrity = random.nextInt(solution.n);
        System.out.println("set celebrity = " + celebrity);
        for (int i = 0; i < solution.n; i++) {
            if (celebrity == i) {
                continue;
            }
            solution.graph[i][celebrity] = 1;
        }

        System.out.println("found celebrity = " + solution.findCelebrity(solution.n));
    }
}
