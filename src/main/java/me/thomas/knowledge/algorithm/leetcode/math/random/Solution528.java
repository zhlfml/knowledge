package me.thomas.knowledge.algorithm.leetcode.math.random;

import java.util.Random;

/**
 * 给你一个 下标从 0 开始 的正整数数组w ，其中w[i] 代表第 i 个下标的权重。
 * <p>
 * 请你实现一个函数pickIndex，它可以 随机地 从范围 [0, w.length - 1] 内（含 0 和 w.length - 1）选出并返回一个下标。选取下标 i的 概率 为 w[i] / sum(w) 。
 * <p>
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3)= 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3)= 0.75（即，75%）。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution528.java, v 0.1 2022/6/30 10:34 xinsheng2.zhao Exp $
 */
public class Solution528 {

    private final int[]  prefixSum;
    private final Random random;

    public Solution528(int[] w) {
        if (w == null || w.length == 0) {
            throw new IllegalArgumentException("w is empty array");
        }
        random = new Random();
        // 这里的前缀和长度需要和原始数组保持一致，因为最终返回的数原数组下标的位置。
        prefixSum = new int[w.length];
        prefixSum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + w[i];
        }
    }

    /**
     * 思路：计算前缀和，如数组[1, 9, 2, 1]的前缀和为[1, 10, 12, 13]，那么我们随机选中[0, 13) + 1 => [1, 13]这13个数字中的一个，其概率相等。确定数字后只需左边界二分查找数在的位置即可。
     *
     * @return
     */
    public int pickIndex() {
        // randomValue: [1, prefixSum[prefixSum.length - 1]]
        int randomValue = random.nextInt(prefixSum[prefixSum.length - 1]) + 1; /* 选中前缀和中[1, prefixSum[prefixSum.length - 1]]概率相等 */
        return leftBound(randomValue);
    }

    private int leftBound(int target) {
        int low = 0, high = prefixSum.length - 1; /* 经过测试这里high是否需要减1都可以通过测试，而且官方题解也减了1，后期遇到相同的问题需要关注下。*/
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (prefixSum[mid] < target) {
                low = mid + 1;
            } else if (prefixSum[mid] >= target) {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        Solution528 solution = new Solution528(new int[] { 1, 9, 2, 1 });
        for (int i = 1; i <= solution.prefixSum[solution.prefixSum.length - 1]; i++) {
            System.out.println(solution.pickIndex());
        }
    }
}
