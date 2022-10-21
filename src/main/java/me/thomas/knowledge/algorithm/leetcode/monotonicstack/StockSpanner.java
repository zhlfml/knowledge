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
     * 思路：这题的常规思路是使用数组保存每次放入的数字，每放入一个数需要从后向前看，找到第一个大于准备要放入的数为止，然后计算出跨度。
     * 但是这种思路明显性能低下，得寻找一种更高效的存储方式，由此想到了单调栈 -- 通过单调栈压缩存储空间又不影响最终的结果。
     * 但是使用单调栈之后，原来的位置信息无法保存了，因此得单调栈中得使用只有两列的一维数组，一列存放价格数值，另一列存放当前数值的索引位置。
     * <p>
     * 使用单调栈时为什么是栈中的数小于等于准备放入的数时弹出呢？因为需要找到第一个大于准备要放入的数为止，中间那些数被删除后对结果没有影响。
     */
    public StockSpanner() {
        stack = new Stack<>();
        stack.push(new int[] { Integer.MAX_VALUE, 0 }); /* 从官网的思路学到的，可避免每次判空 */
    }

    /**
     * 关键点在于能想出 beforePop和afterPop，分别表示压栈之前最顶部的index和压栈之后最顶部的index，之差表示跨度。
     * 但是这里定义的最大连续日数包括今天，所以最后需要再加1。
     */
    public int next(int price) {
        int beforePop = stack.peek()[1];
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            stack.pop();
        }
        int afterPop = stack.peek()[1];
        stack.push(new int[] { price, beforePop + 1 });
        return beforePop - afterPop + 1;
    }
}
