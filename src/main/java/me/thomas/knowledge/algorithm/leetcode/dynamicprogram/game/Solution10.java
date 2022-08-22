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
        if (s == null) {
            throw new IllegalArgumentException("s is null");
        }
        if (p == null) {
            throw new IllegalArgumentException("p is null");
        }
        int m = p.length(), n = s.length();
        boolean[][] dp = new boolean[m + 1][n + 1]; /* 含义：p的前i个字符能否被匹配s的前j个字符的结果为dp[i][j] */
        dp[0][0] = true;  /* base case: 前0个字符都是空字符串，可以匹配上 */
        for (int i = 1; i <= m; i++) { /* 正则p从1开始测试 */
            for (int j = 0; j <= n; j++) { /* 字符串s从0开始测试 */
                if (matches(p, s, i, j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 2][j]; /* 匹配0次：等于去掉正则的.* */
                    if (matches(p, s, i - 1, j)) {
                        /*
                            以a*ab匹配aaaaab为例演示a*匹配一次的过程：
                            1. 首先dp[2][0] = true，此时a*匹配0次。
                            2. 其次dp[2][1] = dp[2][0], 相当于去掉了s的最后一个a，算是匹配了一次，即依赖a*能否匹配0次的结果。
                            3. 依次类推dp[2][2] = dp[2][1], dp[2][3] = dp[2][2] ...
                         */
                        dp[i][j] = dp[i][j] || dp[i][j - 1]; /* 匹配1次：等于去掉字符串s的最后一个字符，再看.*能否匹配0次。 */
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
        System.out.println(solution.isMatch("", "a*"));
        System.out.println(solution.isMatch("a", "a*"));
        System.out.println(solution.isMatch("aaaaab", "a*ab"));
        System.out.println(solution.isMatch("abce", "abcd*e"));
        System.out.println(solution.isMatch("abcdddde", "abcd*e"));
        System.out.println(solution.isMatch("aab", "c*a*b"));
        System.out.println(solution.isMatch("mississippi", "mis*is*p*."));
    }
}
