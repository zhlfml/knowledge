package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.range;

/**
 * 516. 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * <p>
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution516.java, v 0.1 2022/8/7 14:36 xinsheng2.zhao Exp $
 */
public class Solution516 {

    /**
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null) {
            return 0;
        }
        int len = s.length();
        if (len < 2) {
            return len;
        }
        return longestPalindromeSubseq_dp1(s);
    }

    /**
     * 将dp定义成一维数组
     */
    int longestPalindromeSubseq_dp1(String s) {
        char[] str = s.toCharArray();
        int len = s.length();
        int[] dp = new int[len]; /* dp含义：字符串s从i（i是当前遍历的行数）到j的最大回文子序列的长度为dp[j] */
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
        }
        /*
         * 从下向上从左到右遍历
         */
        for (int i = len - 1; i >= 0; i--) {
            int pre = 0;
            for (int j = i + 1; j < len; j++) {
                int tmp = dp[j]; /* Step 1. 没覆盖前dp[j]对应存储dp[i+1][j] */
                if (str[i] != str[j]) {
                    // dp[j]在赋新值之前存储的值对应dp[i+1][j]
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                } else {
                    dp[j] = 2 + pre; /* Step 3. 因此prev对应存储dp[i+1][j-1] */
                }
                pre = tmp; /* Step 2. 进入下一次循环（j++）后，prev对应存储dp[i+1][j-1] */
            }
        }
        return dp[len - 1];
    }

    /**
     * 将dp定义成二维数组
     */
    int longestPalindromeSubseq_dp2(String s) {
        char[] str = s.toCharArray();
        int len = s.length();

        int[][] dp = new int[len][len]; /* dp含义：字符串s从i到j的最大回文子序列的长度为dp[i][j] */
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        /*
         * 从下向上从左到右遍历
         * 计算最长回文子序列时分为两种情况
         *  1. i和j所在的字符不等，那么dp[i][j]的最大回文子序列就是Math.max(dp[i + 1][j], dp[i][j - 1])
         *  2. i和j所在的字符相等, 又细分为两种情况 -- 其实这两种可以合为一种，因为i和j相邻时，dp[i + 1][j - 1] == 0，所以可以合并。
         *  2.1 i和j相邻，那么dp[i][j]==2
         *  2.2 i和j不相邻，那么dp[i][j]==2+dp[i + 1][j - 1]
         */
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (str[i] != str[j]) {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                } else {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                }
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        Solution516 solution = new Solution516();
        System.out.println(solution.longestPalindromeSubseq("abbabbxa"));
    }
}
