package me.thomas.knowledge.algorithm.leetcode.dynamicprogram;

import java.util.HashMap;
import java.util.Map;

/**
 * 873. 最长的斐波那契子序列的长度
 * 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 * <p>
 * n >= 3
 * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 * <p>
 * （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 *
 * @author xinsheng2.zhao
 * @version Id: Solution873.java, v 0.1 2022/10/19 22:46 xinsheng2.zhao Exp $
 */
public class Solution873 {

    /**
     * 这题的关键是缓存了数以及其对应的下标，可以在续子序列时以O(1)的时间复杂度得出是否有后续子数列。
     */
    public int lenLongestFibSubseq(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            cache.put(arr[i], i);
        }
        return lenLongestFibSubseq_dp(arr, cache);
    }

    /**
     * 动态规划算法，保存以arr[j],arr[k]结尾的斐波那契子数列的长度，时间复杂度O(n^2)
     */
    int lenLongestFibSubseq_dp(int[] arr, Map<Integer, Integer> cache) {
        int n = arr.length;
        int longest = 0;
        int[][] dp = new int[n][n]; /* 含义：以arr[j],arr[k]结尾的斐波那契子数列的长度为dp[j][k]，其前序arr[i]也是通过斐波那契数列的定义快速计算出其位置的 */
        for (int j = 0; j < n - 1; j++) {
            for (int k = j + 1; k < n; k++) { /* 内外层循环的条件与朴素的暴力法一致 */
                // 由于剪枝后的`dp[j][k] = 2`赋值代码可能会走不到，所以必须在取值数设置最小值为2
                // dp[j][k] = 2; /* 任意两个数结束的序列长度至少等于2 */
                int i = cache.getOrDefault(arr[k] - arr[j], -1);
                /* i的取值范围 [0, j) */
                if (i < 0) {
                    continue;
                }
                if (i >= j) { /* 性能优化的关键点：如果arr[k] - arr[j]后的值所在的位置i大于等于了j，说明此时arr[k]太大了，k++后的数更大，所以可以快速剪枝 */
                    break;
                }
                dp[j][k] = Math.max(dp[i][j], 2) /* 任意两个数结束的序列长度至少等于2 */ + 1;
                longest = Math.max(longest, dp[j][k]); /* longest只要被赋值，长度至少为3，所以return处的longest不用做长度判断 */
            }
        }
        return longest;
    }

    /**
     * 朴素的暴力算法，每次都计算以arr[i],arr[j]开头的斐波那契子数列的长度，时间复杂度O(n^2)
     */
    int lenLongestFibSubseq_my(int[] arr, Map<Integer, Integer> cache) {
        int n = arr.length;
        int longest = 0;
        int a, b, next, length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                a = arr[i];
                b = arr[j];
                length = 2;
                // 检测是否存在后续子序列
                do {
                    next = cache.getOrDefault(a + b, -1);
                    if (next > 0) {
                        a = b;
                        b = arr[next];
                        length++;
                    }
                } while (next > 0);
                longest = Math.max(longest, length);
            }
        }
        return longest >= 3 ? longest : 0;
    }

    public static void main(String[] args) {
        Solution873 solution873 = new Solution873();
        System.out.println(solution873.lenLongestFibSubseq(new int[] { 2, 4, 7, 8, 9, 10, 14, 15, 18, 23, 32, 50 }));
    }

}
