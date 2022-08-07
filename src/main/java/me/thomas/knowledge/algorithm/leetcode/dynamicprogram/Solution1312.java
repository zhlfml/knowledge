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
     * 思路：动态规划直接求出最少操作次数。
     */
    public int minInsertions(String s) {
        if (s == null) {
            return 0;
        }
        int len = s.length();
        if (len < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[][] dp = new int[len][len]; /* dp含义：字符串s从i到j成为回文子串的最少操作次数 */
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (str[i] != str[j]) {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                } else if (j - i > 1) { /* 其实无需判断`j - i > 1` */
                    dp[i][j] = dp[i + 1][j - 1];
                }
            }
        }
        return dp[0][len - 1];
    }

}
