package me.thomas.knowledge.algorithm.leetcode.monotonicstack;

import java.util.Stack;

/**
 * 907. 子数组的最小值之和
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * <p>
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution907.java, v 0.1 2022/10/29 21:51 xinsheng2.zhao Exp $
 */
public class Solution907 {

    final int MOD = 1_000_000_007;

    /**
     * 思路：贡献法 + 单调栈
     * 确定边界：arr[i]的边界(l,r)，需确保arr[l+1, r-1]的任意一个数都大于等于arr[i]，换言之arr[i]是里面最小的。
     * 参考文章<a href="https://leetcode.cn/problems/sum-of-subarray-minimums/solution/-by-muse-77-367z/>图解LeetCode</a>
     */
    public int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        long answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= n; i++) {
            int num = i < n ? arr[i] : 0;
            while (!stack.isEmpty() && arr[stack.peek()] >= num) { /* 由于对称性，这里判断条件是 `>` 还是 `>=` 对结果无影响 */
                int index = stack.pop(); // 作为目前为止的最大值弹出
                answer += (long) (i - index) * (index - (stack.isEmpty() ? -1 : stack.peek())) * arr[index];
            }
            stack.push(i);
        }
        return (int) (answer % MOD);
    }

    public static void main(String[] args) {
        Solution907 solution907 = new Solution907();
        System.out.println(solution907.sumSubarrayMins(new int[] { 1, 3, 4, 2, 6 }));
    }
}
