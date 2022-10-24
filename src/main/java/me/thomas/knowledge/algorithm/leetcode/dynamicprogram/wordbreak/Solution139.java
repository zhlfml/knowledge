package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.wordbreak;

import java.util.List;

/**
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution139.java, v 0.1 2022/9/14 23:07 xinsheng2.zhao Exp $
 */
public class Solution139 {

    /**
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     * 注意，你可以重复使用字典中的单词。
     * <p>
     * 思路：有网友说是背包型动态规划，但我看像区间型动态规划。
     * 递推关系式：若dp[i][j]可以被拼出且dp[j][k]可以被拼出，那么dp[i][k]也可以被拼出
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len + 1][len + 1]; /* 含义：字符串s的区间[i, j]是否可以利用字典中出现的单词拼接出 */
        // base case：0字符长度都为true
        for (int i = 0; i <= len; i++) {
            dp[i][i] = true;
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j <= len; j++) {
                for (int k = j; k > i; k--) {
                    dp[i][j] = dp[k][j] && isWordInDict(s.substring(i, k), wordDict);
                    if (dp[i][j]) {
                        break;
                    }
                }
            }
        }
        return dp[0][len];
    }

    boolean isWordInDict(String s, List<String> wordDict) {
        for (String dict : wordDict) {
            if (dict.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
