package me.thomas.knowledge.algorithm.leetcode.dynamicprogram;

import java.util.Arrays;

/**
 * 1235. 规划兼职工作
 * 你打算利用空闲时间来做兼职工作赚些零花钱。
 * <p>
 * 这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
 * <p>
 * 给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。
 * <p>
 * 注意，时间上出现重叠的 2 份工作不能同时进行。
 * <p>
 * 如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1235.java, v 0.1 2022/10/22 15:39 xinsheng2.zhao Exp $
 */
public class Solution1235 {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[] { startTime[i], endTime[i], profit[i] };
        }

        // Order by endTime, startTime ASC
        Arrays.sort(jobs, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);

        // 想到了两种思路：
        // 第一种思路: 1. 找出最大的endTime，第一层循环是 1..maxEndTime，第二层遍历jobs。（这种思路行不通）
        // 第二种思路: 内外层都遍历jobs。
        int[] dp = new int[n]; /* 含义：jobs从0到i的最大报酬为dp[i] */
        // base case: 第0个job的报酬为jobs[0][2] -- 注：也可以定义dp = new int[n + 1], base case为dp[0] = 0, 返回dp[n]; 似乎更通用些。
        dp[0] = jobs[0][2];
        // 以何种路径遍历数组？
        for (int i = 1; i < n; i++) {
            dp[i] = jobs[i][2]; /* 仅选当前工作的报酬，担心与前面任何job都续不上 */
            // 向前寻找并检测哪一个job的工作开始时间与此job没有冲突。-- 注：这里可以优化成通过二分法查找，但是向前遍历会简单些。
            for (int j = i - 1; j >= 0; j--) {
                if (jobs[j][1] <= jobs[i][0]) { /* 前一个job的结束时间小于等于当前时间的开始时间，则可以续上 */
                    dp[i] += dp[j]; /* 选第i个job的最大报酬为当前job的报酬 + 之前时间上没有冲突的报酬 */
                    break;
                }
            }
            dp[i] = Math.max(dp[i - 1], dp[i]); /* 选当前job和不选当前job，取报酬的最大值 */
        }
        return dp[n - 1];
    }
}
