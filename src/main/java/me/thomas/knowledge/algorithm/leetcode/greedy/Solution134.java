package me.thomas.knowledge.algorithm.leetcode.greedy;

import java.util.Arrays;

/**
 * 134. 加油站
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution134.java, v 0.1 2022/9/26 21:09 xinsheng2.zhao Exp $
 */
public class Solution134 {

    /**
     * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
     * 输出: 3
     * 解释:
     * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
     * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
     * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
     * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
     * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
     * 因此，3 可为起始索引。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int gasTotal = Arrays.stream(gas).sum();
        int costTotal = Arrays.stream(cost).sum();
        if (gasTotal < costTotal) { /* 供给量小于消耗量，不能环形行驶一周 */
            return -1;
        }
        /*
         * 每个加油站加油后行驶到下一个加油站后剩余的油量。
         * 问题变成了如何求解数组的连续区间的最大和，但是怎么解决环形呢？-- 复制数组本身到其末尾处。
         */
        int n = gas.length;
        int m = n << 1;
        int[] left = new int[m];
        for (int i = 0; i < m; i++) {
            left[i] = gas[i % n] - cost[i % n];
        }

        int answer = 0;
        int maxStartFrom = 0; /* 数组的连续区间的最大和的开始处 */
        int maxEndHere = 0, /* 到此为止的最大值（包含当前点） */ maxSoFar = 0 /* 目前为止的最大值（不包含当前点） */;
        for (int i = 0; i < m; i++) {
            maxEndHere += left[i];
            if (maxEndHere < 0) {
                maxStartFrom = i + 1; /* 一旦小于0，就尝试以下个下标为起点计算连续区间的最大值 */
                maxEndHere = 0;
            }
            if (maxEndHere > maxSoFar) {
                answer = maxStartFrom;
                maxSoFar = maxEndHere;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution134 solution134 = new Solution134();
        System.out.println(solution134.canCompleteCircuit(new int[] { 1, 2, 3, 4, 5 }, new int[] { 3, 4, 5, 1, 2 }));
    }
}
