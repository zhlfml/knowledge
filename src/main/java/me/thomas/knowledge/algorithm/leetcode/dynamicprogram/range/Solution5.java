package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.range;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution5.java, v 0.1 2022/8/7 15:29 xinsheng2.zhao Exp $
 */
public class Solution5 {

    /**
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     */
    public String longestPalindrome(String s) {
        if (s == null) {
            return "";
        }
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int start = 0;
        int longest = 0;
        char[] str = s.toCharArray();
        boolean[][] dp = new boolean[len][len]; /* dp含义：字符串s从i到j是否为回文子串 */
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (str[i] == str[j]) {
                    if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                    // 如果是回文子串，则记录长度标识和起始位置
                    if (dp[i][j] && j - i > longest) {
                        start = i;
                        longest = j - i;
                    }
                }
            }
        }
        return s.substring(start, start + longest + 1);
    }

    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
        System.out.println(solution5.longestPalindrome("dabbac"));
        System.out.println(solution5.longestPalindrome("dabac"));
        System.out.println(solution5.longestPalindrome("cbbd"));
        System.out.println(solution5.longestPalindrome("abcd"));
    }
}
