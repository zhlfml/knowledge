package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.range;

/**
 * 712. 两个字符串的最小ASCII删除和
 * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution721.java, v 0.1 2022/8/7 15:20 xinsheng2.zhao Exp $
 */
public class Solution712 {

    /**
     * 输入: s1 = "sea", s2 = "eat"
     * 输出: 231
     * 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
     * 在 "eat" 中删除 "t" 并将 116 加入总和。
     * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
     * 思路：当剩余的子序列为最长公共子序列时，删除的字符的ASCII值最小。
     */
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1]; /* 含义：s1的前i个字符与s2的前j个字符的最小删除和为dp[i][j] */
        dp[0][0] = 0; // base case: 如果两个字符串长度都为0，那么无需删除任何字符。
        // base case: 如果一个字符为空，那么另外一个字符只能全部删除。
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // 无需删除字符
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1] + s2.charAt(j - 1), dp[i - 1][j] + s1.charAt(i - 1)); // 找删除代价小的
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        Solution712 solution = new Solution712();
        System.out.println(solution.minimumDeleteSum("sea", "eat"));
    }
}
