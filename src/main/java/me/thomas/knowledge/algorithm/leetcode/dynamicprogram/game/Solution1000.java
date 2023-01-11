package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game;

/**
 * 1000. 合并石头的最低成本
 * 有 N 堆石头排成一排，第 i 堆中有 stones[i] 块石头。
 * <p>
 * 每次移动（move）需要将连续的 K 堆石头合并为一堆，而这个移动的成本为这 K 堆石头的总数。
 * <p>
 * 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1000.java, v 0.1 2022/7/1 18:10 xinsheng2.zhao Exp $
 */
public class Solution1000 {

    /**
     * 输入：stones = [3,2,4,1], K = 2
     * 输出：20
     * 解释：
     * 从 [3, 2, 4, 1] 开始。
     * 合并 [3, 2]，成本为 5，剩下 [5, 4, 1]。
     * 合并 [4, 1]，成本为 5，剩下 [5, 5]。
     * 合并 [5, 5]，成本为 10，剩下 [10]。
     * 总成本 20，这是可能的最小值。
     */
    public int mergeStones(int[] stones, int k) {
        int stonesSize = stones.length;
        if (stonesSize == 1) {
            return 0;
        }
        if (k < 2) {
            throw new IllegalArgumentException("k < 2");
        }
        if (stonesSize < k || (stonesSize - k) % (k - 1) > 0) {
            return -1;
        }

        int answer = 0;
        while (stonesSize > 1) {
            int minSum = Integer.MAX_VALUE, minEnd = 0, sumK = 0;
            for (int i = 0; i < stonesSize; i++) {
                if (i < k) {
                    sumK += stones[i];
                    minSum = sumK;
                    minEnd = i;
                } else {
                    sumK = sumK - stones[i - k] + stones[i];
                    if (sumK < minSum) {
                        minSum = sumK;
                        minEnd = i;
                    }
                }
            }
            answer += minSum;
            stones[minEnd + 1 - k] = minSum;
            for (int i = minEnd + 1; i < stonesSize; i++) {
                stones[minEnd + 2 - k] = stones[i];
            }
            stonesSize -= k - 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution1000 solution = new Solution1000();
        System.out.println(solution.mergeStones(new int[] { 3, 5, 1, 2, 6 }, 3));
    }
}
