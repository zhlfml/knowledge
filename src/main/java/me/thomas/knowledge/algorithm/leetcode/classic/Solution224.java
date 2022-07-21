package me.thomas.knowledge.algorithm.leetcode.classic;

import java.util.Stack;

/**
 * 224. 基本计算器
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 * @author xinsheng2.zhao
 * @version Id: Solution224.java, v 0.1 2022/7/20 16:57 xinsheng2.zhao Exp $
 */
public class Solution224 {

    /**
     * 输入：s = "(1+(4+5+2)-3)+(6+8)"
     * 输出：23
     */
    public int calculate(String s) {
        char sign = '+';
        int number = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isSpaceChar(c)) {
                continue;
            }
            if (Character.isDigit(c)) {
                number += number * 10 + (c - '0');
            }
            if (!Character.isDigit(c) || i == s.length() - 1) {
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

    public static void main(String[] args) {
        Solution224 solution = new Solution224();
        System.out.println(solution.calculate("1 + 2/2 + 3+4+5*2/3"));
    }

}
