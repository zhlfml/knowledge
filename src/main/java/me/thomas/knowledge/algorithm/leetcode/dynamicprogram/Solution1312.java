package me.thomas.knowledge.algorithm.leetcode.dynamicprogram;

/**
 * 1312. 让字符串成为回文串的最少插入次数
 * 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
 * <p>
 * 请你返回让 s 成为回文串的 最少操作次数 。
 * <p>
 * 「回文串」是正读和反读都相同的字符串。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution1312.java, v 0.1 2022/8/7 18:57 xinsheng2.zhao Exp $
 */
public class Solution1312 {

    /**
     * 思路：先求出最长回文子序列，再将总长度减去最长回文子序列的长度，结果就是需要插入的字符数。
     */
    public int minInsertions(String s) {
        if (s == null) {
            return 0;
        }
        return s.length() - longestPalindromeSubSeq(s);
    }

    /**
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     */
    int longestPalindromeSubSeq(String s) {
        if (s == null) {
            return 0;
        }
        int len = s.length();
        if (len < 2) {
            return len;
        }
        char[] str = s.toCharArray();
        int[][] dp = new int[len][len]; /* dp含义：字符串s从i到j的最大回文子序列的长度为dp[i][j] */
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        /*
         * 从下向上从左到右遍历
         * 计算最长回文子序列时分为两种情况
         *  1. i和j所在的字符不等，那么dp[i][j]的最大回文子序列就是Math.max(dp[i + 1][j], dp[i][j - 1])
         *  2. i和j所在的字符相等, 又细分为两种情况
         *  2.1 i和j相邻，那么dp[i][j]==2
         *  2.2 i和j不相邻，那么dp[i][j]==2+dp[i + 1][j - 1]
         */
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (str[i] != str[j]) {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                } else {
                    if (j - i == 1) {
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = 2 + dp[i + 1][j - 1];
                    }
                }
            }
        }
        return dp[0][len - 1];
    }
}
