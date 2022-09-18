package me.thomas.knowledge.algorithm.leetcode.string;

/**
 * 28. 找出字符串中第一个匹配项的下标
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution28.java, v 0.1 2022/9/18 18:29 xinsheng2.zhao Exp $
 */
public class Solution28 {

    /**
     * 输入：haystack = "sadbutsad", needle = "sad"
     * 输出：0
     * 解释："sad" 在下标 0 和 6 处匹配。
     * 第一个匹配项的下标是 0 ，所以返回 0 。
     */
    public int strStr(String haystack, String needle) {
        return kmpMatch(haystack, needle);
    }

    /**
     * 构建查找模式的前缀数组 -- 从数组下标1开始的字符i，当前有最多n个字符与pattern的开头的前n个字符完全相同，那么prefix[i] = n。
     */
    int[] buildPrefix(String pattern) {
        int[] prefix = new int[pattern.length()];
        int maxSameChars = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (maxSameChars > 0 && pattern.charAt(maxSameChars) != pattern.charAt(i)) {
                maxSameChars = prefix[maxSameChars - 1]; /* 取相同前缀部分继续测试，需要减1的原因是这里的maxSameChars是长度，映射到数组下标需要减1 */
            }
            /*
            如果与前缀的下一个字符相同，那么共同长度+1
             */
            if (pattern.charAt(maxSameChars) == pattern.charAt(i)) {
                ++maxSameChars;
            }
            prefix[i] = maxSameChars;
        }
        return prefix;
    }

    int kmpMatch(String str, String pattern) {
        int[] prefix = buildPrefix(pattern);
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (str.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution28 solution = new Solution28();
        System.out.println(solution.strStr("mississippi", "issip"));
    }
}
