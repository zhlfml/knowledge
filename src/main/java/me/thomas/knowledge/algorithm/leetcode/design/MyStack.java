package me.thomas.knowledge.algorithm.leetcode.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * <p>
 * 实现 MyStack 类：
 * <p>
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 * <p>
 * 注意：
 * 你只能使用队列的基本操作 —— 也就是push to back、peek/pop from front、size 和is empty这些操作。
 * 你所使用的语言也许不支持队列。你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列, 只要是标准的队列操作即可。
 *
 * @author xinsheng2.zhao
 * @version Id: MyStack.java, v 0.1 2022/7/3 16:16 xinsheng2.zhao Exp $
 */
public class MyStack {

    private int            last;
    private Queue<Integer> pushQueue;
    private Queue<Integer> popQueue;

    public MyStack() {
        last = 0;
        pushQueue = new LinkedList<>();
        popQueue = new LinkedList<>();
    }

    public void push(int x) {
        pushQueue.offer(x);
        last = x;
    }

    /**
     * 思路：只要pushQueue不为空，可以将pushQueue装载到popQueue中（除最后一个），并将弹出的最后一个元素作为结果返回。
     * 如果pushQueue空了，就对调pushQueue和popQueue。
     */
    public int pop() {
        if (pushQueue.isEmpty()) {
            Queue<Integer> tmp = pushQueue;
            pushQueue = popQueue;
            popQueue = tmp;
        }
        int size = pushQueue.size();
        while (--size > 0) {
            last = pushQueue.poll();
            popQueue.offer(last);
        }
        return pushQueue.poll();
    }

    /**
     * 每次调用 top 都保证栈不为空
     */
    public int top() {
        return last;
    }

    public boolean empty() {
        return pushQueue.isEmpty() && popQueue.isEmpty();
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.pop());
        myStack.push(3);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
    }
}
