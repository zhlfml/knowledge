package me.thomas.knowledge.algorithm.leetcode.monotonicstack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个整数数组temperatures，表示每天的温度，返回一个数组answer，其中answer[i]是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用0 来代替。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution739.java, v 0.1 2022/7/3 14:37 xinsheng2.zhao Exp $
 */
public class Solution739 {

    /**
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        // 关键点：需要从后向前遍历数组 -- 其实与遍历顺序无关，正着遍历也可以
        /*for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                stack.pop();
            }
            answer[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }*/
        // 这里演示正序遍历，与倒序遍历的区别是：1. 需要使用pop弹出的值 2. 弹出的数值的需要立即更新结果。
        // 关键是不管正序还是倒序遍历，弹出的规则"写法上"没有改变，但内涵却变了，需要细品。
        int n = temperatures.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                answer[index] = i - index;
            }
            stack.push(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution739 solution = new Solution739();
        System.out.println(Arrays.toString(solution.dailyTemperatures(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 })));
    }
}
