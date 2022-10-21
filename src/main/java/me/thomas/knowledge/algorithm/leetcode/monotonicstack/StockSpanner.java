package me.thomas.knowledge.algorithm.leetcode.monotonicstack;

import java.util.Stack;

/**
 * 901. 股票价格跨度
 * 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 * <p>
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * <p>
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution901.java, v 0.1 2022/10/21 23:18 xinsheng2.zhao Exp $
 */
public class StockSpanner {

    Stack<int[]> stack;

    /**
     * 思路：单调栈 -- 通过单调栈压缩存储空间。
     */
    public StockSpanner() {
        stack = new Stack<>();
    }

    /**
     * 关键点在于能想出 beforePop和afterPop，分别表示压栈之前最顶部的index和压栈之后最顶部的index，之差表示跨度。
     * 但是这里定义的最大连续日数包括今天，所以最后需要再加1。
     */
    public int next(int price) {
        int beforePop = stack.isEmpty() ? 0 : stack.peek()[1];
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            stack.pop();
        }
        int afterPop = stack.isEmpty() ? 0 : stack.peek()[1];
        stack.push(new int[] { price, beforePop + 1 });
        return beforePop - afterPop + 1;
    }
}
