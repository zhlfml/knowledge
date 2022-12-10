package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.wordbreak;

import com.google.common.collect.ImmutableList;

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
     * 递推关系式：若dp[i][j]可以被拼出且dp[j][k]可以被拼出，那么dp[i][k]也可以被拼出。
     * 得知这是动态规划题之后，发现此题和gameOfFivePowers（由C语言实现）非常类似，所以可以参考那道题的思路。
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int len = s.length();
        boolean[] dp = new boolean[len + 1]; // 含义：字符串s的前i个字符能否通过字典拼出的结果为dp[i];
        dp[0] = true; // base case: 没有字符时能拼出
        for (int i = 1; i <= len; i++) {
            for (String word : wordDict) {
                int w = word.length();
                if (s.startsWith(word, i - w)) {
                    dp[i] = dp[i - w];
                    if (dp[i]) {
                        break;
                    }
                }
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        Solution139 solution139 = new Solution139();
        System.out.println(solution139.wordBreak("catsandog", ImmutableList.of("cats", "dog", "sand", "and", "cat")));
        System.out.println(solution139.wordBreak("applepenapple", ImmutableList.of("apple", "pen")));
    }

}
