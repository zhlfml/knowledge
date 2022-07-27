package me.thomas.knowledge.algorithm.leetcode.design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value需要返回 -1
 *
 * @author xinsheng2.zhao
 * @version Id: MaxQueue.java, v 0.1 2022/7/3 15:49 xinsheng2.zhao Exp $
 */
public class MaxQueue {

    private final Queue<Integer> queue;
    private final Deque<Integer> deque;

    /**
     * 思路：单调队列
     * 依赖普通队列和双端队列两个属性，
     * 普通队列的重用是提供数据先进先出的能力。
     * 双端队列的作用是维护最值。
     */
    public MaxQueue() {
        queue = new ArrayDeque<>();
        deque = new LinkedList<>();
    }

    public int max_value() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }

    public void push_back(int value) {
        queue.offer(value);
        while (!deque.isEmpty() && deque.peekLast() < value) {
            deque.pollLast();
        }
        deque.offerLast(value);
    }

    public int pop_front() {
        int value = queue.isEmpty() ? -1 : queue.poll();
        if (value > 0 && !deque.isEmpty() && deque.peekFirst() == value) {
            deque.pollFirst();
        }
        return value;
    }
}
