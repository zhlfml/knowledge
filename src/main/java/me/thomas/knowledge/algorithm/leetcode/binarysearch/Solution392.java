package me.thomas.knowledge.algorithm.leetcode.binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * <p>
 * 进阶：
 * <p>
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * @author xinsheng2.zhao
 * @version Id: Solution392.java, v 0.1 2022/7/24 15:28 xinsheng2.zhao Exp $
 */
public class Solution392 {

    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) {
            return true;
        }
        if (t == null || t.length() == 0) {
            return false;
        }

        List<Integer>[] stats = new List[26];
        for (char c = 'a'; c <= 'z'; c++) {
            stats[c - 'a'] = new ArrayList<>();
        }
        // 对t中每个字符出现的位置进行预处理
        for (int i = 0; i < t.length(); i++) {
            stats[t.charAt(i) - 'a'].add(i);
        }
        int position = -1;
        // 二分查找s是否为t的子序列
        for (int i = 0; i < s.length(); i++) {
            List<Integer> positions = stats[s.charAt(i) - 'a'];
            if (positions.isEmpty()) {
                return false;
            }
            position = leftBound(positions, position + 1);
            if (position == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 通过左侧边界二分查找，返回大于target的最小数。
     * <br/>
     * <b>对于搜索左侧边界的二分查找，有一个特殊性质：当 val 不存在时，得到的索引恰好是比 val 大的最小元素索引。</b>
     */
    int leftBound(List<Integer> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low < list.size() ? list.get(low) : -1;
    }

    public static void main(String[] args) {
        Solution392 solution = new Solution392();
        System.out.println(solution.isSubsequence("abc", "ahbgdc"));
    }
}
