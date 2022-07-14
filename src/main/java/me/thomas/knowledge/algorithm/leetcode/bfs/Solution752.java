package me.thomas.knowledge.algorithm.leetcode.bfs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 752. 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * <p>
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * <p>
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * <p>
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution752.java, v 0.1 2022/7/13 22:23 xinsheng2.zhao Exp $
 */
public class Solution752 {
    /**
     * 初始局面
     */
    final String initPhase = "0000";

    /**
     * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * 输出：6
     * 解释：
     * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
     * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
     * 因为当拨动到 "0102" 时这个锁就会被锁定。
     */
    public int openLock(String[] deadends, String target) {
        int steps = 0;
        Set<String> deadPhases = Arrays.stream(deadends).collect(Collectors.toSet());
        Set<String> searchedPhases = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(initPhase);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String phase = queue.poll();
                assert phase != null;
                if (phase.equals(target)) {
                    return steps;
                }
                if (deadPhases.contains(phase)) {
                    continue;
                }
                if (searchedPhases.contains(phase)) {
                    continue;
                }
                searchedPhases.add(phase);
                for (int j = 0; j < 4; j++) {
                    char[] copy = phase.toCharArray().clone();
                    copy[j] = turnDown(copy[j]);
                    queue.offer(new String(copy));
                    copy[j] = turnUp(copy[j]); /* 还原 */
                    copy[j] = turnUp(copy[j]);
                    queue.offer(new String(copy));
                }
            }
            steps++;
        }

        return -1;
    }

    /**
     * 将0-9的字符减1
     */
    char turnDown(char c) {
        if (c >= '1' && c <= '9') {
            return (char) (c - 1);
        }
        return '9';
    }

    /**
     * 将0-9的字符加1
     */
    char turnUp(char c) {
        if (c >= '0' && c <= '8') {
            return (char) (c + 1);
        }
        return '0';
    }
}
