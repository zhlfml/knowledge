package me.thomas.knowledge.algorithm.leetcode.monotonicstack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 316. 去除重复字母
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution316.java, v 0.1 2022/10/22 21:21 xinsheng2.zhao Exp $
 */
public class Solution316 {

    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int[] stats = new int[26];
        for (char c : s.toCharArray()) {
            stats[c - 'a']++;
        }
        int[] visited = new int[26]; /* 记录在deque里的字符 */
        Deque<Integer> deque = new LinkedList<>();
        for (char c : s.toCharArray()) {
            // 确保只能放入一次
            if (visited[c - 'a'] > 0) {
                continue;
            }
            while (!deque.isEmpty() && deque.peek() > c - 'a' && stats[deque.peek()] > 1) {
                int val = deque.poll();
                stats[val]--;
                visited[val]--;
            }
            deque.offer(c - 'a');
            visited[c - 'a']++;
        }

        // 从deque弹出字符，拼成字符串
        StringBuilder sb = new StringBuilder(deque.size());
        while (!deque.isEmpty()) {
            sb.append((char) (deque.pollFirst() + 'a'));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution316 solution316 = new Solution316();
        System.out.println(solution316.removeDuplicateLetters("ecbacba"));
    }
}
