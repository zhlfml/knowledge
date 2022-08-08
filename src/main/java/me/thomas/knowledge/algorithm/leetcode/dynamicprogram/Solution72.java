package me.thomas.knowledge.algorithm.leetcode.dynamicprogram;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * @author xinsheng2.zhao
 * @version Id: Solution72.java, v 0.1 2022/8/8 21:07 xinsheng2.zhao Exp $
 */
public class Solution72 {

    /**
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0) {
            return word2 == null ? 0 : word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }

        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1]; /* 含义：字符串s1的前i个字符转换成字符串s2前j个字符需要dp[i][j]个操作 */

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 选择
                    dp[i][j] = 1 + min(dp[i - 1][j] /* 删除 */, dp[i][j - 1] /* 插入 */, dp[i - 1][j - 1] /* 替换 */);
                }
            }
        }
        return dp[m][n];
    }

    int min(int... a) {
        int answer = a[0];
        for (int i = 1; i < a.length; i++) {
            answer = Math.min(answer, a[i]);
        }
        return answer;
    }
}
