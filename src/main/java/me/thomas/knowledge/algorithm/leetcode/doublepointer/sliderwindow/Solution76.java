package me.thomas.knowledge.algorithm.leetcode.doublepointer.sliderwindow;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution76.java, v 0.1 2022/7/1 11:00 xinsheng2.zhao Exp $
 */
public class Solution76 {

    public String minWindow(String s, String t) {
        String EMPTY = "";
        if (s == null || s.length() == 0) {
            return EMPTY;
        }
        if (t == null || t.length() == 0) {
            return EMPTY;
        }
        int slen = s.length(), tlen = t.length();
        if (slen < tlen) {
            return EMPTY;
        }

        int[] stats = new int[256];
        for (char c : t.toCharArray()) {
            stats[c]++;
        }
        int foundChars = 0;
        int start = 0, len = slen + 1;
        int left = 0, right = 0;
        while (right < slen) {
            if (stats[s.charAt(right++)]-- > 0) {
                foundChars++;
                // 滑动窗口中移动left指针的while一定要判断关键的状态
                while (foundChars == tlen) {
                    if (++stats[s.charAt(left)] > 0) {
                        if (right - left < len) {
                            start = left;
                            len = right - left;
                        }
                        foundChars--;
                    }
                    left++;
                }
            }
        }
        if (len > slen) {
            return EMPTY;
        }
        return s.substring(start, start + len);
    }

    public static void main(String[] args) {
        Solution76 solution = new Solution76();
        // output: BANC
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
    }
}
