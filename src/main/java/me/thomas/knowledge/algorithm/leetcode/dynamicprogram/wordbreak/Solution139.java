package me.thomas.knowledge.algorithm.leetcode.dynamicprogram.wordbreak;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<String> wordDictSet = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1]; /* 含义：字符串s的区间从0到i是否可以利用字典中出现的单词拼接出记为dp[i] */
        // base case：0字符长度为true
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            if (wordDictSet.contains(s.substring(0, i))) {
                dp[i] = true;
            }
            // s[0..i]不是一个完整的单词，那么尝试分解，查看其是否为二个或以上单词组合而成。
            else {
                for (int j = i - 1; j >= 1; j--) { /* 倒过来判断性能更高 */
                    dp[i] = dp[j] && wordDictSet.contains(s.substring(j, i));
                    if (dp[i]) {
                        break;
                    }
                }
            }
        }
        return dp[len];
    }

}
