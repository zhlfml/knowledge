package me.thomas.knowledge.algorithm.leetcode.monotonicqueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 单调队列的实现
 * <p>
 * 实现思路：使用双端队列模拟单调队列，每次push时需要维护单调队列的单调性。获取最大（小）值其实只是获取单调队列的头元素。
 * pop时需要判断当前被pop的元素是不是最大的元素（因为可能被压扁不存在了），只有肯定时才能删除单调队列的头元素。
 *
 * @author xinsheng2.zhao
 * @version Id: MonotonicQueue.java, v 0.1 2022/10/30 10:35 xinsheng2.zhao Exp $
 */
public class MonotonicQueue<E extends Comparable<E>> {

    /**
     * 普通queue，用于实时维护当前窗口的所有元素
     */
    private final Queue<E> queue;

    /**
     * 维护当前窗口的最大值的单调队列，单调递减队列
     */
    private final Deque<E> maxDeque;

    /**
     * 维护当前窗口的最小值的单调队列，单调递增队列
     */
    private final Deque<E> minDeque;

    public MonotonicQueue() {
        queue = new LinkedList<>();
        maxDeque = new LinkedList<>();
        minDeque = new LinkedList<>();
    }

    /**
     * 压入元素
     */
    public void push(E val) {
        queue.offer(val);
        // 单调递减序列
        while (!maxDeque.isEmpty() && maxDeque.peekLast().compareTo(val) < 0) {
            maxDeque.pollLast();
        }
        maxDeque.offerLast(val);
        // 单调递增序列
        while (!minDeque.isEmpty() && minDeque.peekLast().compareTo(val) > 0) {
            minDeque.pollLast();
        }
        minDeque.offerLast(val);
    }

    /**
     * 弹出元素 -- 只有单调队列头元素与普通队列头元素相同时，才能真正执行弹出操作。
     */
    public E pop() {
        E e = queue.poll();
        if (e == maxDeque.peekFirst()) {
            maxDeque.pollFirst();
        }
        if (e == minDeque.peekFirst()) {
            minDeque.pollFirst();
        }
        return e;
    }

    public E max() {
        return maxDeque.peekFirst();
    }

    public E min() {
        return minDeque.peekFirst();
    }

    public int size() {
        return queue.size();
    }
}
