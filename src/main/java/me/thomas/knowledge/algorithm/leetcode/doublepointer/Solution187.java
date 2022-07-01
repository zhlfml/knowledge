package me.thomas.knowledge.algorithm.leetcode.doublepointer;

import java.util.*;

/**
 * DNA序列由一系列核苷酸组成，缩写为'A','C','G'和'T'.。
 * <p>
 * 例如，"ACGAATTCCG"是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 * <p>
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的长度为10的序列(子字符串)。你可以按 任意顺序 返回答案。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution187.java, v 0.1 2022/6/30 17:34 xinsheng2.zhao Exp $
 */
public class Solution187 {

    public List<String> findRepeatedDnaSequences(String s) {
        final int BASE = 4;
        final int MAX_LENGTH = 10;
        int len = s.length();
        Set<Integer> seen = new HashSet<>();
        Set<String> answer = new HashSet<>();
        int hash = 0;
        int left = 0, right = 0;
        while (right < len) {
            hash = hash * BASE + map(s.charAt(right++));
            while (right - left >= 10) {
                if (seen.contains(hash)) {
                    answer.add(s.substring(left, right));
                } else {
                    seen.add(hash);
                }
                hash = hash - map(s.charAt(left)) * (int) Math.pow(BASE, MAX_LENGTH - 1);
                left++;
            }
        }
        return new ArrayList<>(answer);
    }

    private int map(char c) {
        int value;
        switch (c) {
            case 'C':
                value = 1;
                break;
            case 'G':
                value = 2;
                break;
            case 'T':
                value = 3;
                break;
            default:
                value = 4;
                break;
        }
        return value;
    }

    public static void main(String[] args) {
        Solution187 solution = new Solution187();
        solution.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
    }

}
