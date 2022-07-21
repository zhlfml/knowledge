package me.thomas.knowledge.algorithm.leetcode.classic;

import java.util.Stack;

/**
 * 227. 基本计算器 II
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * 整数除法仅保留整数部分。
 * <p>
 * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
 * <p>
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution227.java, v 0.1 2022/7/21 23:31 xinsheng2.zhao Exp $
 */
public class Solution227 {

    /**
     * 输入：s = " 3+5 / 2 "
     * 输出：5
     */
    public int calculate(String s) {
        char sign = '+';
        int number = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            }
            if (!Character.isDigit(c) && !Character.isSpaceChar(c) || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stack.push(number);
                        break;
                    case '-':
                        stack.push(-number);
                        break;
                    case '*':
                        stack.push(stack.pop() * number);
                        break;
                    case '/':
                        stack.push(stack.pop() / number);
                        break;
                    default:
                        System.err.println("not supported operator: " + sign);
                        break;
                }
                // 重置sign和number变量
                sign = c;
                number = 0;
            }
        }
        int answer = 0;
        while (!stack.isEmpty()) {
            answer += stack.pop();
        }
        return answer;
    }
}
