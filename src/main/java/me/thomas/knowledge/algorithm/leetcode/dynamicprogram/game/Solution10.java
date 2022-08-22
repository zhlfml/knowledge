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
        boolean[][] dp = new boolean[m + 1][n + 1]; /* 含义：p的前i个字符能否被匹配s的前j个字符的结果为dp[i][j] */
        dp[0][0] = true;  /* base case: 前0个字符都是空字符串，可以匹配上 */
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (matches(p, s, i, j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 2][j]; /* 匹配0次 */
                    if (matches(p, s, i - 1, j)) {
                        dp[i][j] = dp[i][j] || dp[i][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    boolean matches(String p, String s, int i, int j) {
        if (j == 0) {
            return false;
        }
        if (p.charAt(i - 1) == '.') {
            return true;
        }
        return p.charAt(i - 1) == s.charAt(j - 1);
    }

    public static void main(String[] args) {
        Solution10 solution = new Solution10();
        System.out.println(solution.isMatch("a", "a*"));
        System.out.println(solution.isMatch("aaaaab", "a*ab"));
        System.out.println(solution.isMatch("abce", "abcd*e"));
        System.out.println(solution.isMatch("abcdddde", "abcd*e"));
        System.out.println(solution.isMatch("aab", "c*a*b"));
        System.out.println(solution.isMatch("mississippi", "mis*is*p*."));
    }
}
