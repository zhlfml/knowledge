package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.game;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution10.java, v 0.1 2022/8/21 22:33 xinsheng2.zhao Exp $
 */
public class Solution10 {

    public boolean isMatch(String s, String p) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("s is empty");
        }
        if (p == null || p.length() == 0) {
            throw new IllegalArgumentException("p is empty");
        }
        int m = p.length(), n = s.length();
        boolean[][] dp = new boolean[m][n]; /* 含义：p[0..i]能否被匹配s[0..j]的结果为dp[i][j] */
        dp[0][0] = isMatch(p.charAt(0), s.charAt(0));
        // 不能因为dp[0][0]==false就提前结束，因为可能存在正则第二个字符是*的情况
        for (int i = 1; i < m; i++) {
            boolean isMultiple = p.charAt(i) == '*';
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j - 1] && isMatch(p.charAt(i), s.charAt(j));
                if (!dp[i][j] && isMultiple) { // 如果不能匹配，那么检查p[i]是否为`*`
                    dp[i][j] = (i >= 2 && dp[i - 2][j]) || isMatch(p.charAt(i - 1), s.charAt(j));
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    boolean isMatch(char regexChar, char normalChar) {
        return regexChar == '.' || normalChar == regexChar;
    }

    public static void main(String[] args) {
        Solution10 solution = new Solution10();
        System.out.println(solution.isMatch("abce", "abcd*e"));
        System.out.println(solution.isMatch("abcdddde", "abcd*e"));
        System.out.println(solution.isMatch("aab", "c*a*b"));
    }
}
